APK TESTED: saucelabs.apk

to run on command line: mvn clean test -DsuiteName=${suite name}

example: mvn clean test -DsuiteName=regression


Jenkins top maven script:
clean test -DsuiteName=${suiteName} -DdeviceName="${deviceName}" -DosVersion="${osVersion}"
