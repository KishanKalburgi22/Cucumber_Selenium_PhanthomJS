Feature: Compare session count on highcharts.com

Scenario: Comparison of session count 

Given I go to highcharts.com
When I Mouseover on Jan 5, 2018 date in the sessions curve in graph			
And I click on the Jan 5, 2018 date in the sessions curve in graph	
Then Session values in both the cases should be same