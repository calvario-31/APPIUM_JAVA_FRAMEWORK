# APPIUM JAVA FRAMEWORK
* Apk Tested: 

        saucelabs.apk

* Run on command line: 

        mvn clean test -DsuiteName=${suite name}

* Example: 

        mvn clean test -DsuiteName=regression

* Jenkins top maven script:

        clean test -DsuiteName=${suiteName} -DdeviceName="${deviceName}" -DosVersion="${osVersion}"
