package com.xyz.apigateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PreFilter extends ZuulFilter {

	  @Override
	  public String filterType() {
	    return "pre";
	  }

	  @Override
	  public int filterOrder() {
	    return 0;
	  }

	  @Override
	  public boolean shouldFilter() {
	    return true;
	  }

	  @Override
	public Object run() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		log.info("PreFilter: called with {} method and url {} ", request.getMethod(), request.getRequestURL().toString());
		return null;
	}
}