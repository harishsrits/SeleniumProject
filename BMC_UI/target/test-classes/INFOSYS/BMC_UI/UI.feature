Feature: product validation

Scenario: Validate the product is added to the wish list
Given Launch the browser
Given Enter URL
When Enter the keyword "amazon" in the search bar
When print all the search results
When Click on the link which takes you to the amazon login page
When Sign in to amazon
When click on all buttons on search & select Electronics
When search for dell computers
When apply the filter of range Rs "30000" to "50000"
When Validate all the products on the first 2 pages are shown in the range of Rs 30000 to 50000
When print all the products on the first 2 pages whose rating is 5 out of 5
When add the first product whose rating is 5 out of 5 to the wish list
Then Validate the product is added to the wish list

