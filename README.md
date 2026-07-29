# Hercules – Appium Mobile Automation

Automated test suite for **Hercules**, the FMCG field-force & distribution management application built for **NAAFCO Group**, running on Android.

Built and maintained by the **QA Automation team, SSL Wireless**.

---

## 📌 Overview

This repository contains the Android automation framework for Hercules, covering the core employee and supervisor workflows of the app end-to-end — login, order booking, delivery, retailer/product search, attendance, leave, work plan, tour plan, and the supervisor approval chain for each of these.

The framework is built with **Appium + Java + TestNG**, follows the **Page Object Model (POM)**, and is designed around the app's **Jetpack Compose UI** (no resource-IDs, obfuscated class names), using resilient text-based `UiSelector` locators instead of ID-based ones.

| | |
|---|---|
| **Application** | Hercules – FMCG Distribution Software (NAAFCO Group) |
| **Platform** | Android (Native / Jetpack Compose) |
| **Automation Type** | Mobile UI Automation |
| **Language** | Java 17 |
| **Test Runner** | TestNG |
| **Driver** | Appium (UiAutomator2) |
| **Design Pattern** | Page Object Model (POM) |
| **Reporting** | Allure, TestNG native reports, custom PDF reports |
| **Owner** | SSL Wireless – QA Automation |

---

## 🗂️ Project Structure

```
SSLHercules/
├── src/
│   ├── main/java/
│   │   ├── base/
│   │   │   └── AndroidBase.java          # Appium driver/session setup & teardown
│   │   ├── pages/                        # Page Object classes (one per module)
│   │   │   ├── LoginPage.java
│   │   │   ├── HomePage.java
│   │   │   ├── OrderPage.java
│   │   │   ├── DeliveryPage.java
│   │   │   ├── MoneyCollectionPage.java
│   │   │   ├── AttendancePage.java
│   │   │   ├── LeavePage.java
│   │   │   ├── WorkPlanPage.java
│   │   │   ├── TourPlanPage.java
│   │   │   ├── HiarchyAttendancePage.java   # Supervisor approval variants
│   │   │   ├── HiarchyLeavePage.java
│   │   │   ├── HiarchyWorkPlanPage.java
│   │   │   └── HiarchyTourPlanPage.java
│   │   ├── properties/
│   │   │   ├── data.properties           # Appium server / device config
│   │   │   └── allure.properties
│   │   └── utils/
│   │       ├── AndroidActions.java       # Gestures, scrolling (W3C PointerInput)
│   │       ├── AppiumUtils.java
│   │       ├── AppiumTestListener.java
│   │       ├── TestLogger.java
│   │       └── PdfReportGenerator.java   # Custom PDF report export
│   └── test/java/
│       ├── tests/                        # 30 TestNG test classes (TC01–TC30) + navigation test
│       ├── testData/                     # JSON test data, one file per scenario/module
│       └── resources/                    # Application APK used for test runs
├── allure-results/ & allure-report/       # Generated Allure evidence & dashboard
├── test-output/                          # Native TestNG HTML/XML reports
├── testng.xml                            # TestNG suite definition
└── pom.xml                               # Maven build & dependencies
```

---

## 🧰 Tech Stack

| Component | Library | Version |
|---|---|---|
| Mobile Driver | Appium Java Client | 10.0.0 |
| Bindings | Selenium Java | 4.35.0 |
| Test Framework | TestNG | 7.11.0 |
| Reporting | Allure TestNG | 2.35.3 |
| JSON Handling | Jackson Databind | 2.17.0 |
| PDF Generation | iText7 (kernel / layout / io) | 7.2.5 |
| AOP (Allure step capture) | AspectJ Weaver | 1.9.21 |
| Build Tool | Maven (Surefire, Compiler) | 3.3.x / 3.13.0 |
| Java | JDK | 17 |

---

## ✅ Prerequisites

Before running the suite, make sure you have:

- **Java 17** installed and on `PATH`
- **Maven** installed
- **Node.js** + **Appium** installed globally (`npm install -g appium`)
- **Android SDK / platform-tools** with `adb` on `PATH`
- A physical Android device or emulator with **USB debugging** enabled
- The Hercules test-build APK (bundled under `src/test/java/resources/`)

---

## ⚙️ Configuration

Device and Appium server settings are configured in:

```
src/main/java/properties/data.properties
```

```properties
ipAddress=127.0.0.1
port=4723
AndroidDeviceName=<your-device-name>
udid=<your-device-udid>
```

> Update `AndroidDeviceName` and `udid` to match the device/emulator you want to run against (get these via `adb devices`).

The Appium server path is currently referenced directly in `AndroidBase.java` — update the local Appium install path there if your machine differs:

```java
.withAppiumJS(new File("<path-to-your-appium>/build/lib/main.js"))
```

---

## ▶️ Running the Tests

1. **Connect a device** and confirm it's visible:
   ```bash
   adb devices
   ```
2. **Run the full suite** via the TestNG XML:
   ```bash
   mvn clean test
   ```
3. **Run a single test class** (e.g. Login):
   ```bash
   mvn test -Dtest=TC01_LoginTest
   ```
4. **Run via `testng.xml`** directly from your IDE (Eclipse/IntelliJ) — right-click `testng.xml` → *Run As TestNG Suite*.

> ⚠️ `testng.xml` currently only wires up `TC01` and `TC02`. To execute the full regression pack, either add the remaining `<class>` entries to the suite or run classes individually via Maven/IDE.

---

## 📋 Test Coverage

The suite currently automates **31 test classes across 11 functional modules**:

| Module | Test Cases | Count |
|---|---|---|
| Authentication | TC01, TC02 | 2 |
| Order Management (Create/Edit/Draft/Overdue) | TC03–TC06, TC08, TC17, TC29, TC30 | 8 |
| Order Listing & Filters | TC20–TC23 | 4 |
| Delivery | TC07, TC18 | 2 |
| Retailer / Product Search | TC09, TC10, TC19 | 3 |
| Prepare Screen Filters | TC11–TC13 | 3 |
| Attendance | TC14, TC25 | 2 |
| Leave Management | TC24, TC26 | 2 |
| Work Plan | TC15, TC27 | 2 |
| Tour Plan | TC16, TC28 | 2 |
| Navigation | Hamburger Menu | 1 |

<details>
<summary><strong>Full test case list (click to expand)</strong></summary>

| TC ID | Test Name | Module | Scenario |
|---|---|---|---|
| TC01 | Login Test | Authentication | Valid login with employee/supervisor credentials |
| TC02 | Invalid Login Test | Authentication | Negative login validation |
| TC03 | Draft Save Test | Order Management | Save an order as draft without submission |
| TC04 | Order – Cash & Cash Products | Order Management | Cash payment, cash-category products |
| TC05 | Order – Cash & Credit Products | Order Management | Cash payment, credit-category products |
| TC06 | Order – Credit & Credit Products | Order Management | Credit payment, credit-category products |
| TC07 | Delivery Test | Delivery | End-to-end order delivery workflow |
| TC08 | Order OverDue Test | Order Management | Overdue payment/order handling |
| TC09 | Product Search – Invalid | Retailer/Product Search | Negative search for non-existent product |
| TC10 | Retailer Search Test | Retailer/Product Search | Valid retailer search |
| TC11 | Prepare – Has OverDue Filter | Prepare / Filters | Filter retailers with overdue balance |
| TC12 | Prepare – Name Filter | Prepare / Filters | Filter retailers by name |
| TC13 | Prepare – Submarket Filter | Prepare / Filters | Filter retailers by submarket |
| TC14 | Attendance Test | Attendance | Punch-In / Punch-Out with toast confirmation |
| TC15 | Work Plan Test | Work Plan | Create and submit a work plan |
| TC16 | Tour Plan Test | Tour Plan | Create a date-driven tour plan entry |
| TC17 | Order List – Edit Test | Order Management | Edit an existing order |
| TC18 | Single Product Delivery Test | Delivery | Delivery for a single-product order |
| TC19 | Retailer Search – Invalid | Retailer/Product Search | Negative search for non-existent retailer |
| TC20 | List – Customer Name Filter | Order Listing / Filters | Filter order list by customer name |
| TC21 | List – SubMarket Filter | Order Listing / Filters | Filter order list by submarket |
| TC22 | List – Order ID Filter | Order Listing / Filters | Filter order list by Order ID |
| TC23 | List – Filter By Date | Order Listing / Filters | Filter order list by date range |
| TC24 | Leave Application Test | Leave | Apply leave via calendar/date-picker |
| TC25 | Supervisor – Attendance Approve | Approval Workflows | Approve subordinate attendance |
| TC26 | Supervisor – Leave Approve | Approval Workflows | Approve subordinate leave |
| TC27 | Supervisor – Work Plan Approve | Approval Workflows | Approve subordinate work plan |
| TC28 | Supervisor – Tour Plan Approve | Approval Workflows | Approve subordinate tour plan |
| TC29 | Order – Cash & Both Products | Order Management | Cash payment, mixed product types |
| TC30 | Order – Credit & Both Products | Order Management | Credit payment, mixed product types |


</details>

---

## 🧩 Framework Design Notes

- **Jetpack Compose UI:** locators are built as text-based `UiSelector` expressions (`textContains`, `textMatches`) rather than resource-ID locators, since Compose exposes no IDs and only obfuscated class names (e.g. `r3.x0`, `android.view.View`). `enforceXPath1: true` is set where needed to avoid UiAutomator2 XPath2 crashes on complex expressions.
- **Toast validation:** toasts are polled with `driver.findElements()` immediately after the triggering action (no pre-sleep), and never queried twice via `.isDisplayed()`. Applied consistently in Login, Attendance, Leave, and supervisor approval tests.
- **Gestures:** scrolling/tapping uses W3C `PointerInput`/`Sequence` actions rather than the deprecated `TouchAction` API.
- **Dynamic content:** Tour Plan uses `LocalDate`-driven locators for date-based cards; Leave Application matches full dates via `content-desc`.
- **Data-driven tests:** every test class pulls its input through a TestNG `@DataProvider`, backed by module-specific JSON files under `src/test/java/testData/`.

---

## 📊 Reporting

| Report Type | Location | Notes |
|---|---|---|
| Allure Report | `allure-report/index.html` | Step-level, screenshot-backed, with history/trend across runs |
| TestNG Native Report | `test-output/` | HTML/XML + JUnit-style XML for CI ingestion |
| Custom PDF Report | Generated via `PdfReportGenerator.java` | Shareable summary independent of Allure/TestNG |

Generate/view the Allure report:
```bash
allure serve allure-results
```

---

## 🚧 Known Open Items

- **TC14 (Attendance – Punch In):** the punch button uses an instance-based locator that can occasionally resolve incorrectly; also sensitive to test-data state (app blocks a second same-day Punch-In). Under active stabilization.
- POM coverage is being expanded further for Attendance, Leave Application, Work Plan Approve, and Tour Plan.
- `testng.xml` currently only references TC01–TC02; full-suite execution requires running classes individually or extending the suite file.

---

## 🗺️ Roadmap

- [ ] Stabilize TC14 punch-in locator + add precondition/data-reset handling
- [ ] Extend `testng.xml` to include the full TC01–TC30 regression pack
- [ ] Wire the suite into CI (GitHub Actions) with auto-published Allure reports
- [ ] Continue POM hardening for remaining Compose-heavy screens

---

## 👤 Maintainer

**SSL Wireless – QA Automation Team**
Project: Hercules Mobile Automation (Android / Appium)