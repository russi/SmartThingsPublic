definition(
    name: "My Garage Toggle",
    namespace: "russi",
    author: "Russi Sahiwal",
    description: "Toggle Garage Open/Close",
    category: "Convenience",
    iconUrl: "https://www.dropbox.com/s/6kxtd2v5reggonq/lightswitch.gif?raw=1",
    iconX2Url: "https://www.dropbox.com/s/6kxtd2v5reggonq/lightswitch.gif?raw=1",
    iconX3Url: "https://www.dropbox.com/s/6kxtd2v5reggonq/lightswitch.gif?raw=1")


preferences {
	section("Master switch for the toggle reference...") {
    	input "masterToggle", "capability.switch", title: "Reference switch", required: true, multiple: false
    }
    
    section("Toggle GARAGE DOOR...") {
	    input "switchesToToggle", "capability.switch", title: "This open/close", required: true, multiple: true
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	subscribe(motionToggler, "motion", toggleSwitches)
}


def toggleSwitches(evt) {
	log.debug "$evt.value"
  
	if (evt.value == "active" && masterToggle.currentSwitch == "off") {
//    	for (thisSwitch in switchesToToggle) {
//        	log.debug "$thisSwitch.label"
//  			thisSwitch.on()
		switchesToToggle.on()
        masterToggle.on()
    } else if (evt.value == "active" && masterToggle.currentSwitch == "on") {
//    	for (thisSwitch in switchesToToggle) {
//        	log.debug "$thisSwitch.label"
//        	thisSwitch.off()
		switchesToToggle.off()
        masterToggle.off()
        }

}