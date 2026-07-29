@echo off
setlocal


REM 4. Generate + open report
call allure generate allure-results --clean -o allure-report
call allure open allure-report

endlocal