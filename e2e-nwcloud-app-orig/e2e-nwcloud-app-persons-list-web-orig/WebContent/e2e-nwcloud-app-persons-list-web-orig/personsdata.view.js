sap.ui.jsview("e2e-nwcloud-app-persons-list-web-orig.personsdata", {

      getControllerName : function() {
         return "e2e-nwcloud-app-persons-list-web-orig.personsdata";
      },

      createContent : function(oController) {
    		//Create an instance of the table control
    		var oTable = new sap.ui.table.Table({
    			title : "Persons List",
    			visibleRowCount : 7,
    			firstVisibleRow : 3,
    			selectionMode : sap.ui.table.SelectionMode.Single,
    		});

    		// toolbar
    		var oTableToolbar = new sap.ui.commons.Toolbar();

    		// first name field
    		var oFirstNameLabel = new sap.ui.commons.Label({
    			id : 'firstNameLabelId',
    			text : 'First Name'
    		});
    		var oFirstNameField = new sap.ui.commons.TextField({
    			id : 'firstNameFieldId',
    			value : '',
    			width : '10em',
    		});
    		oFirstNameLabel.setLabelFor(oFirstNameField);

    		// TODO: label in toolbar results in ToolbarRenderer.render: oToolbarItem must be a ToolbarItem
    		oTableToolbar.addItem(oFirstNameLabel);
    		oTableToolbar.addItem(oFirstNameField);

    		// last name field
    		var oLastNameLabel = new sap.ui.commons.Label({
    			id : 'lastNameLabelId',
    			text : 'Last Name'
    		});
    		var oLastNameField = new sap.ui.commons.TextField({
    			id : 'lastNameFieldId',
    			value : '',
    			width : '10em',
    		});
    		oLastNameLabel.setLabelFor(oLastNameField);

    		// TODO: label in toolbar results in ToolbarRenderer.render: oToolbarItem must be a ToolbarItem
    		oTableToolbar.addItem(oLastNameLabel);
    		oTableToolbar.addItem(oLastNameField);

    		// add button
    		var oAddPersonButton = new sap.ui.commons.Button({
    			id : 'addPersonButtonId',
    			text : "Add Person",
    			press : function() {
    				oController.addNewPerson(sap.ui.getCore().getControl(
    						"firstNameFieldId").getValue(), sap.ui.getCore()
    						.getControl("lastNameFieldId").getValue(), oTable);
    			}
    		});
    		oTableToolbar.addItem(oAddPersonButton);

    		oTable.setToolbar(oTableToolbar);

    		// define the columns and the control templates to be used
    		oTable.addColumn(new sap.ui.table.Column({
    			label : new sap.ui.commons.Label({
    				text : "First Name"
    			}),
    			template : new sap.ui.commons.TextField().bindProperty("value",
    					"firstName"),
    			sortProperty : "name",
    			filterProperty : "name",
    			width : "100px"
    		}));
    		oTable.addColumn(new sap.ui.table.Column({
    			label : new sap.ui.commons.Label({
    				text : "Last Name"
    			}),
    			template : new sap.ui.commons.TextField().bindProperty("value",
    					"lastName"),
    			sortProperty : "lastName",
    			filterProperty : "lastName",
    			width : "200px"
    		}));

    		oTable.bindRows("/persons");

    		return oTable;
        }
});
