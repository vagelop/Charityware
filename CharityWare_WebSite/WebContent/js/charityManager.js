
function init()
{
	 tabSwitch(1,5,'tab_', 'content_');
	 document.getElementById("argc").value = 0;
	 removeChildren(document.getElementById("extra"));
	 removeChildren(document.getElementById("errmsg"));
	 document.getElementById("typeoptions").selectedIndex = 0;
	 document.getElementById("fieldname").value = "";
}


/*----------------------------------------Form Jacascript beyond this point----------------------------------------------------------------*/
/*---------Delete Form------*/
function deleteCurrentForm(siteUrl,id)
{
	//###################Needs Link###########################//
	var frm = new FormObject("");
	frm.formId=id;
	$.ajax({
	    type: "POST",
	    url: siteUrl+"RESTCharity/formService/" + urlHibernate + "/form/delete/",
	    // The key needs to match your method's input parameter (case-sensitive).
	    data: JSON.stringify(frm),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(data)
	    {
	    	alert("Form Deleted");
	    	location.reload(); 
	    },
	    failure: function(errMsg) 
	    {
	        alert("Form could not be deleted at this time");
	        location.reload(); 
	    }
	});
}
/*----------------------------Show form creation wizard----------------------------*/

function FormObject(name)
{
	this.formId;
	this.dateCreated = new Date().getFullYear() + "-" + new Date().getMonth() + "-" + new Date().getDay();
	this.isActive = true;
	this.formName = name;
	this.fields = new Array();
}
function FieldObject(lbl)
{
	this.field_label = lbl;
	this.date_created = new Date().getFullYear() + "-" + new Date().getMonth() + "-" + new Date().getDay();
	this.isRequired = false;
	this.isActive = true;
	this.field_selections = new Array();
	this.fieldType = new FieldTypeObject();
}
function FieldTypeObject()
{
	this.fieldType_id;
	this.isActive = true;
}
function FieldTypeSelectionObject()
{
	this.field_selection_value;
}

function createForm(siteUrl)
{
	/*Setup Form*/
	var baseObject = new FormObject($('#formname').val());
	
	$('#rowsetrows').children().each(function(e){
		/*Setup fields*/
		var Field = new FieldObject($(this).find('#name_'+ e).val());
		Field.isRequired = $(this).find('#isReq_'+ e).is(':checked');
			/*Setup field Types*/
			var fieldType = new FieldTypeObject();
			fieldType.fieldType_id = $(this).find('#type_'+ e).val();
			
				/*Drop Down Menu checks*/
				if(!new RegExp("^[0-9]*$").test(fieldType.fieldType_id))
				{
					/*Get Drop down values*/
					var DDOptions =  new RegExp("[^0-9*].*").exec(fieldType.fieldType_id);
					DDOptions = DDOptions[0].substring(1, DDOptions[0].length-1);
					DDOptions = DDOptions.split(",");
					/*get field type id*/
					fieldType.fieldType_id = new RegExp("^[0-9*]").exec(fieldType.fieldType_id);	
					/*add drop down items to field type*/
					$.each(DDOptions, function(e){
						var FieldTypeSelection = new FieldTypeSelectionObject();
						FieldTypeSelection.field_selection_value = DDOptions[e].substring(1, DDOptions[e].length-1);
						Field.field_selections.push(FieldTypeSelection);
					});
				}
				/*
				if(Field.field_selections.length = 0)
				{
					fieldType.field_selections = null;	
				}*/
			
			/*Add field types to fields*/	
			Field.fieldType = fieldType;
		
		/*Add fields to form*/	
		baseObject.fields.push(Field);	
	});
	
	/*if(baseObject.fields.length == 0)
	{
		baseObject.fields=null;
	}*/


	var JsonText = JSON.stringify(baseObject);
	//alert(JsonText);
	//###################### Needs Link#########################
	$.ajax({
	    type: "POST",
	    url: siteUrl+"RESTCharity/formService/" + urlHibernate + "/forms/insertForm",
	    // The key needs to match your method's input parameter (case-sensitive).
	    data: JsonText,
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(data)
	    {
	    	alert("Form created: " + data);
	    	location.reload(); 
	    },
	    failure: function(errMsg) 
	    {
	        alert("Form could not be created at this time:" + errMsg);
	        location.reload(); 
	    }
	});
	
	
}
function enableCheckboxes(form)
{
	var formElems = form.getElementsByTagName('INPUT');
    for (var i = 0; i < formElems.length; i++)
    {  
       if (formElems[i].type == 'checkbox')
       { 
          formElems[i].disabled = false;
       }
    }
}
function onRowTypeChanged()
{
	var select = document.getElementById("typeoptions");
	var extra = document.getElementById("extra");
	hide(extra);
	removeChildren(extra);
	whatToAppend(extra,select);
}

function whatToAppend(extra,select)
{
	switch(select.selectedIndex)
	{
	case getIndexOfDropdownOption() :
		{
		var combo = document.createElement("select");
		combo.id = "currenumvalues";
		var inputValue = createTextBox("enumcurritem","");
		var btnAdd = createButton("Add",
				function() {
					txt = document.getElementById("enumcurritem");
					if(txt.value != "")
					{
						opt = document.createElement("option");
						opt.value=txt.value;
						opt.text = txt.value;
						var append = true;
						if(!combo.hasChildNodes())
							append = true;
						else
						{
							for(var i = 0; i < combo.options.length;i++)
							{
								if(opt.value == combo.options[i].value)
								{
									append = false;
									break;
								}
							}
						}
						if(append) combo.appendChild(opt);
						txt.value = "";
					}
			
			}		
		);		
		var subdiv2 = document.createElement("div");
	    subdiv2.appendChild(combo);
	    var subdiv1 = document.createElement("div");
		subdiv1.appendChild(inputValue);
		subdiv1.appendChild(btnAdd);
		removeChildren(extra);
		extra.appendChild(subdiv1);
		extra.appendChild(subdiv2);
		unhide(extra);
		break;
		}
	default : hide(extra); removeChildren(extra); break;
	}
}

function createButton(text,onclickhandler,id)
{
	var btn = document.createElement("button");
	btn.type= "button";
	btn.appendChild(document.createTextNode(text));
	btn.onclick = onclickhandler;
	if(typeof id !== "undefined")
		btn.id = id;
	return btn;
}

function getCurrentRowTypeId()
{
	var opt = document.getElementById("typeoptions");
    var ctype = opt.options[opt.selectedIndex].value;
    var valArray = new Array();
    if(opt.selectedIndex == getIndexOfDropdownOption())
    {
    	var cmb = document.getElementById("currenumvalues");
    	
    	for(var i = 0; i < cmb.options.length; i++)
	    	valArray[i] = cmb.options[i].text;
    	
    	ctype += JSON.stringify(valArray);
    }
    return ctype;
}


function getIndexOfDropdownOption()
{
	var select = document.getElementById("typeoptions");
	for(var i = 0; i < select.options.length; i++)
	{
		if(select.options[i].text == "Dropdown")
			return i;
	
	}
	return -1;
}
function getCurrentRowTypeName()
{
	
	    var opt = document.getElementById("typeoptions");
	    var ctype = opt.options[opt.selectedIndex].text;
	    //TODO comment this out when you're done with it
	    var valArray = new Array();
	    if(opt.selectedIndex == getIndexOfDropdownOption())
	    {
	    	var cmb = document.getElementById("currenumvalues");
	    	for(var i = 0; i < cmb.options.length; i++)
		    	valArray[i] = cmb.options[i].text;
	    	
	    	ctype += JSON.stringify(valArray);

	    }
	   
	    return ctype;
}
function showFormWizard()
{
	var wizard = document.getElementById("formwizard");
	unhide(wizard);
}

function addRow()
{
	errMsg = document.getElementById("errmsg");
	try
	{
		hide(errMsg);
		addMe = constructRow();
		document.getElementById("rowsetrows").appendChild(addMe);
		
	}
	catch(err)
	{
		unhide(errMsg);
		errMsg.style.color="#FF0000";
		errMsg.innerHTML = err;
	}
}

function constructRow()
{
	checkWizard();
	var row = document.getElementById("rowsetrows");
    var argcbox = document.getElementById("argc");
    var count=0;
    if(!argcbox.hasAttribute("value"))
    {
    	count = 0;
    }
    else
    {
    	count = parseInt(argcbox.value);
    }
	var divelem = document.createElement("div");

	var inputName = createTextBox("name_"+count,document.getElementById("fieldname").value,true);
	var labelName = createLabel("Name:",inputName.id);
	var inputType = createTextBox("type_"+count+"_name",getCurrentRowTypeName(),true);	
	var labelType = createLabel("Type:",inputType.id);   
	var hiddenType = document.createElement("input");
	hiddenType.type="hidden";
	hiddenType.value=getCurrentRowTypeId();
	hiddenType.id="type_"+count;
	hiddenType.name=hiddenType.id;
    divelem.appendChild(labelName);
    divelem.appendChild(inputName);
    divelem.appendChild(labelType);
    divelem.appendChild(inputType);
    divelem.appendChild(hiddenType);
    
    
    var req = document.createElement("input");
    req.type = "checkbox";
    req.id = "isReq_"+count;
    req.name = req.id;
    req.checked = document.getElementById("rowrequired").checked;
    req.disabled = true;
    var reqLabel = createLabel("Mandatory",req.id);
    divelem.appendChild(reqLabel);
    divelem.appendChild(req);
    
    var btnRemove = createButton("Remove row", 
    		function() {
    			row.removeChild(divelem);
    			var countbox = document.getElementById("argc");
    			if(countbox.hasAttribute("value")) //better safe than sorry
    			{
    				var ct = parseInt(countbox.value);
    				if(ct > 0)
    					countbox.value = ct-1;
    			}
    		}	
    );
    
    divelem.appendChild(btnRemove);
    
    
    count++;
    argcbox.value = count;
    return divelem;
    
}
function checkWizard()
{
	var name = document.getElementById("fieldname");
	if(name.value == "")
		throw "Please input a field name";
	//TODO: check if type selected is combo then the list must be non-empty!
}
function createTextBox(id,value,readonly)
{
	var txtb = document.createElement("input");
	txtb.type="text";
	txtb.id = id;
	txtb.name = id;
	txtb.value = value;
	readonly = (typeof readonly === "undefined") ? false : readonly;
	txtb.readOnly = readonly;
	return txtb;
}
function createLabel(text,forId)
{
	var lb = document.createElement("label");
	lb.setAttribute("for",forId);
	lb.appendChild(document.createTextNode(text));
	return lb;
}
/*--------------------------View all form data--------------------*/
function viewCurrentFormData(siteUrl,id)
{
	$.get(siteUrl+"RESTCharity/formService/form/"+urlHibernate+"/id/"+id, function(data) {

		var currentData = document.getElementById("currentformdatafill");
		removeChildren(currentData);
		unhide(currentData.parentNode);
		
		if(data.fields.length > 0)
		{
			var theLegend = createLegend("Form "+data['formName']+" data");
			currentData.appendChild(theLegend);
			var dataTable = document.createElement("table");
			var headerRow = document.createElement("tr");
			for(var i = 0; i < data.fields.length; i++)
			{
				var th = document.createElement("th");
				th.appendChild(document.createTextNode(data.fields[i].field_label));
				headerRow.appendChild(th);
			}
			dataTable.appendChild(headerRow);
	
			
			function entryObj(MyID)
			{
				this.recordId = MyID;
				this.properties = [];
			}
			
			var bigarray = [];
			
			for(var i = 0; i < data.fields.length; i++)
			{
				
				for(var x = 0; x < data.fields[i].filledForms.length; x++)
				{
					var key = data.fields[i].filledForms[x].record_id;
					var key2 = data.fields[i].field_label;
					
					if(bigarray[key] == undefined)
					{
						
						bigarray[key] = new entryObj(key);
						bigarray[key].properties[key2] = data.fields[i].filledForms[x].value;
					}else{
						bigarray[key].properties[key2] = data.fields[i].filledForms[x].value;
					}
				}	
				
				
			}
	
			for(var j = 0 ; j < bigarray.length; j++)
			{
				var row = document.createElement("tr");			
				for(var i = 0; i < data.fields.length; i++)
				{
					var cell = document.createElement("td");
					try{
						//console.log(bigarray[j].recordId);
						cell.appendChild(document.createTextNode(bigarray[j].properties[data.fields[i].field_label] || ""));
						//data.fields[i].filledForms[j].record_id +data.fields[i].filledForms[j].value
					}catch(err)
					{
						cell.appendChild(document.createTextNode(""));
					}
					row.appendChild(cell);
	
				}
				dataTable.appendChild(row);
			}
			
			
			dataTable.className = "gridtable";
			if(bigarray.length > 0)
			{
				currentData.appendChild(dataTable);
				window.scrollTo(0,dataTable.offsetTop);
			}
			else
			{
				currentData.appendChild(document.createTextNode("Sorry, this table has no data"));

			}
		}
		else
		{
			currentData.appendChild(document.createTextNode("Sorry, this table has no fields defined"));
		}

	});
	
}

/*------------------------------View form Structure----------------------------*/
function writeFormStructure(siteUrl,id) {
	//var colArray = JSON.parse(respText);
	$.get(siteUrl+'RESTCharity/formService/form/' + urlHibernate + '/id/' + id, function(data) {
		colArray = data['fields'];
		var currentRows = document.getElementById("currentformstructurefill");
		removeChildren(currentRows);
		unhide(currentRows.parentNode);
		
		
		if(colArray.length > 0)
		{
			var theLegend = createLegend("Form " + data['formName'] +" structure");
			currentRows.appendChild(theLegend);
			var structureTable = document.createElement("table");
			var header = document.createElement("tr");
			var nameHead = document.createElement("th");
			nameHead.appendChild(document.createTextNode("Column name"));
			var typeHead = document.createElement("th");
			typeHead.appendChild(document.createTextNode("Column type"));
			header.appendChild(nameHead);
			header.appendChild(typeHead);
			structureTable.appendChild(header);
			for(var i = 0 ; i < colArray.length; i++)
			{
				var row = document.createElement("tr");
				var nameRow = document.createElement("td");
				nameRow.appendChild(document.createTextNode(colArray[i].field_label));
				var typeRow = document.createElement("td");
				typeRow.appendChild(document.createTextNode(colArray[i].fieldType.field_Description));
				row.appendChild(nameRow);
				row.appendChild(typeRow);
				structureTable.appendChild(row);
			}
			structureTable.className = "gridtable";
			currentRows.appendChild(structureTable);
		}
		else
		{
			currentRows.appendChild(document.createTextNode("Sorry, this table has no columns defined"));
		}
		
		
	});
	
}

/*-------------------------------------------------Generic Functions--------------------------------*/
function createLegend(text)
{
	var l = document.createElement("legend");
	l.appendChild(document.createTextNode(text));
	return l;
}

function removeChildren(elem)
{
	while(elem.hasChildNodes())
		elem.removeChild(elem.lastChild);
}

function unhide(element)
{	
	element.className = "yesdisplay";
}


function hideCurrentFormStructure()
{
	hide(document.getElementById("currentformstructure"));
	removeChildren(document.getElementById("currentformstructurefill"));
}
function hideCurrentFormData()
{
	hide(document.getElementById("currentformdata"));
	removeChildren(document.getElementById("currentformdatafill"));
}
/*function getCurrentFormId()
{
	var myforms = document.getElementById("myformslist");
	return myforms.options[myforms.selectedIndex].value;
}*/
function onCurrentFormChanged()
{
	hideCurrentFormStructure();
	hideCurrentFormData();
}

function hide(element)
{
	element.className = "nodisplay";
}
function hideFormWizard()
{
	var wizard = document.getElementById("formwizard");
	hide(wizard);
}