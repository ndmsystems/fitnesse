// Copyright (C) 2003-2009 by Object Mentor, Inc. All rights reserved.
// Released under the terms of the CPL Common Public License version 1.0.
package fitnesse.responders.run;

import fitnesse.FitNesseContext;
import fitnesse.authentication.SecureOperation;
import fitnesse.authentication.SecureResponder;
import fitnesse.authentication.SecureTestOperation;
import fitnesse.http.Request;
import fitnesse.http.Response;
import fitnesse.http.SimpleResponse;
import fitnesse.html.template.HtmlPage;

public class TestListResponder implements SecureResponder {

  String testId = null;
  
  public Response makeResponse(FitNesseContext context, Request request) {
    SimpleResponse response = new SimpleResponse();
    
    response.setContent(html(context));

    return response;
  }

  private String html(FitNesseContext context) {
    HtmlPage page = context.pageFactory.newPage();
    page.addTitles("Currently running tests");
    page.put("runningTestingTracker", SuiteResponder.runningTestingTracker);
    page.setMainTemplate("testListPage.vm");
    return page.html();
  }

  public SecureOperation getSecureOperation() {
    return new SecureTestOperation();
  }

}
