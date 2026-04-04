package com.mcp.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcp.service.TestExecutor;

@RestController
@RequestMapping("/mcp")
public class MCPController {

	
	@PostMapping("/run")
    public Map<String, String> runTest(@RequestBody Map<String, String> request) {

        String suiteName = request.get("suite");

        String suitePath = switch (suiteName) {
            case "smoke" -> "src/test/resources/testrunners/testng_smoke.xml";
            case "regression" -> "src/test/resources/testrunners/testng_regression.xml";
            default -> "src/test/resources/testrunners/testng_suite.xml";
        };

        String result = TestExecutor.runSuite(suitePath);

        return Map.of(
                "status", result,
                "report", "test-output/index.html"
        );
    }
}
	
	

