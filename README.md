#UI Test Automation Project

This project contains automated UI tests for the --- Careers page using Java and Selenium WebDriver. The tests are designed following the Page Object Model (POM) pattern and include various test scenarios for the careers page functionality.

## Project Structure
src/
├── main/
│   └── java/
│       └── pages/
│           ├── BasePage.java
│           ├── HomePage.java
│           └── CareersPage.java
└── test/
└── java/
└── tests/
├── BaseTest.java
└── CareersPageTest.java
```

## Technologies Used

- Java 21
- Selenium WebDriver 4.16.1
- TestNG
- Maven

## Test Scenarios

The project includes the following test scenarios:

1. `testCareersPageBlocks()`: Verifies the presence of main content blocks on the careers page
   - Locations block
   - Teams block
   - Life at --- block

2. `testQAJobsFiltering()`: Tests the job filtering functionality
   - Navigates to QA jobs page
   - Filters jobs by location (Istanbul, Turkey)
   - Verifies jobs list is displayed

3. `testQAJobsFilteringAndContent()`: Validates job listing content
   - Filters QA jobs by location
   - Verifies job positions contain "Quality Assurance"
   - Confirms correct department and location information

4. `testViewRoleRedirection()`: Tests the job application flow
   - Clicks on a job listing
   - Verifies redirection to Lever application form
   - Validates URL and form presence

## Key Features

- Implements Page Object Model (POM) design pattern
- Uses optimized CSS selectors for element location
- Includes comprehensive assertions for test validation
- Implements clean and readable code structure
- Handles dynamic elements and loading states
- Includes proper error handling and logging

## Prerequisites

- Java JDK 21 or higher
- Maven
- Chrome Browser
- ChromeDriver (matching your Chrome version)

## Running the Tests

1. Clone the repository:
```bash
git clone https://github.com/UgurKaanYesil/ugur-kaan-yesil-case.git
```

2. Navigate to project directory:
```bash
cd ugur-kaan-yesil-case
```

3. Run tests using Maven:
```bash
mvn clean test
```

## Project Requirements Met

✅ Uses Java + Selenium WebDriver  
✅ No BDD frameworks used  
✅ Fully implements Page Object Model  
✅ Uses optimized CSS selectors  
✅ Includes assertions in test cases  
✅ Maintains clean and readable code

## Notes

- Tests are designed to handle dynamic content loading
- Includes proper wait mechanisms for better stability
- Implements scrolling and hover actions for interactive elements
- Handles multiple browser windows/tabs
- Includes detailed error logging for debugging

## Author

Uğur Kaan Yeşil

## License

This project is licensed under the MIT License - see the LICENSE file for details