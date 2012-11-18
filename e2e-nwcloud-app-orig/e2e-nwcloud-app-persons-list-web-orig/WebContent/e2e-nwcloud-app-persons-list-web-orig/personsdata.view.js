sap.ui.jsview("e2e-nwcloud-app-persons-list-web-orig.personsdata", {

      getControllerName : function() {
         return "e2e-nwcloud-app-persons-list-web-orig.personsdata";
      },

      createContent : function(oController) {
    	  // create the button instance
    	  var myButton = new sap.ui.commons.Button("btn");

    	  // set properties, e.g. the text (there is also a shorter way of setting several properties)
    	  myButton.setText("Hello World!");

    	  // attach an action to the button's "press" event (use jQuery to fade out the button)
    	  myButton.attachPress(function(){$("#btn").fadeOut();});

    	  return myButton;
      }

});
