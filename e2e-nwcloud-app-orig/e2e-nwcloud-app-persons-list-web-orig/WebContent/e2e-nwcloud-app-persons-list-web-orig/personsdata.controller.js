sap.ui.controller("e2e-nwcloud-app-persons-list-web-orig.personsdata", {


/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
*/
	  onInit: function() {
		    var odataModel = new sap.ui.model.odata.ODataModel(personsListOdataServiceUrl);
		    this.getView().setModel(odataModel);
		  },

		  addNewPerson : function( sFirstName, sLastName, oTable ) {
		    var _this = this;
		    _this.odataServiceUrl = personsListOdataServiceUrl;

		    jQuery.ajax({
		       url : _this.odataServiceUrl + "/Person?$format=json",
		       type : 'POST',
		       contentType : 'application/json',
		       data : JSON.stringify({
		                               firstName : sFirstName,
		                               lastName : sLastName
		                            }),
		       success : function(data) {
		         _this.getView().getModel().refresh();
		         // TODO: workaround because refresh() does not rerender the control
		         oTable.unbindRows().bindRows("/Person");
		         // TODO: after refresh the user should receive a success message
		       },
		       error : function(jqXHR, textStatus, errorThrown) {
		         // TODO improve error handling
		         sap.ui.commons.MessageBox.alert("Failed to add person: " + textStatus+ "\n" + errorThrown);
		       }
		    });
		  },

/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
*/
//   onBeforeRendering: function() {
//
//   },

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
*/
//   onAfterRendering: function() {
//
//   },

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
*/
//   onExit: function() {
//
//   }

});
