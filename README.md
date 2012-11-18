e2e-nwcloud-app-persons-list-scenario
=====================================

This repository contains the contents of the end-to-end NetWeaver Cloud Application Scenario Tutorial.

The description of the tutorial has been published on SAP Community Network
http://scn.sap.com/community/developer-center/cloud-platform/content?filterID=content~objecttype~objecttype%5Bblogpost%5D

General Remarks of the Git Repository Contents
==============================================

  The contents of the repository was "almost" created as described in the tutorial in the SCN blog but with the
  following two exceptions

  1. The directory "e2e-nwcloud-app" was not created at "<local-path-to nw-cloud-sdk>\samples" (see section 2.1) but at
     C:\git\e2e-nwcloud-app-persons-list-scenario\ which is the <local-path-to-e2e-nwcloud-app-git-repo>

     Reason:
     -------
     It would be not the natural way to put a git repository somewhere inside the sdk just to make sure that the
     projects are at some predefined location. As stated in the tutorial it is not mandatory to locate the projects
     inside the NW SDK samples directory but can be created anywhere. The git repo with the created e2e-nwcloud-app
     projects demonstrated that this is really the case.

  2. The three projects which are created during the tutorial should be named according to the scenario description
       - e2e-nwcloud-app
       - e2e-nwcloud-app-jpa-model
       - e2e-nwcloud-app-persons-list-web

     But we re-named them for this git repo and added to all a "-orig" string at the end of the respective name. I.e. 
     the three projects to will be created with the following names
       - e2e-nwcloud-app-orig
       - e2e-nwcloud-app-jpa-model-orig
       - e2e-nwcloud-app-persons-list-web-orig
     
     Note, this change will not only reflect the project name directly, but always when the corresponding name(s) 
     appear (e.g. a folder "e2e-nwcloud-app" which is created will be rename to "e2e-nwcloud-app-orig", a Maven
     artifact id "e2e-nwcloud-app-persons-list-web" will be renamed to "e2e-nwcloud-app-persons-list-web-orig", etc.)

     Reason: 
     -------
     By doing this you are able to compare your created sources easily with the original "-orig" projects, as you can
     import these projects from the git repository at the same time into your Eclipse IDE (if we would have created 
     them with the described name you would get a project name conflict).

  After each section where code was created as part of the tutorial a corresponding "section" commit has been done.
  This first tutorial commit is "2.1 Create e2e-nwcloud-app-persons-list-web Project for SAPUI5 Application 
  Development" (the entire sections 1 and 5, or sub sections like 4.3, where the user does not create any source code
  into an eclipse project does not have a corresponding commit in the git repository)
  
  Where an additional (compared to the tutorial) file was created (e.g. a ".gitignore" file), then this is explicitly 
  mentioned in the commit message. 
  
  