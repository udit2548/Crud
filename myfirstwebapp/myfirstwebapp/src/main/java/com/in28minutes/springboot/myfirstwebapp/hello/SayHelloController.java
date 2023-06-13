package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	// say-hello-->Hello, Hey What are you learning today !!	
 @RequestMapping("say-hello")
 @ResponseBody
	public String SayHello(){
		return "Hello, Hey What are you learning today !!";
	}
 @RequestMapping("say-hello-html")
 @ResponseBody
	public String SayHelloHtml(){
	   StringBuffer sb = new StringBuffer();
	   sb.append("<html>");
	   sb.append("<head>");
	   sb.append("<title>");
	   sb.append("My first html page");
	   sb.append("</title>");
	   sb.append("</head>");
	   sb.append("<body>");
	   sb.append("<h1>Here is the html body...</h1>");
	   sb.append("</body>");
	   sb.append("</html>");
	   return sb.toString();
	}
 // Had to add dependency in pom.xml for jsp
// <dependency>
//	<groupId>org.apache.tomcat.embed</groupId>
//	<artifactId>tomcat-embed-jasper</artifactId>
//	<scope>provided</scope>
//</dependency>
 @RequestMapping("say-hello-jsp")
	public String SayHelloJsp(){
		return "sayHello";
	}
}
