function daysInMonth(year, month) {
    return (year == " " ? new Date(0, month, 0).getDate() : new Date(year, month, 0).getDate());
}

function setDayList(objYear, objMonth, objDay) {
	var month = objMonth.options[objMonth.selectedIndex].value;
    var day = objDay.options[objDay.selectedIndex].value;
    var year = objYear.options[objYear.selectedIndex].value;
    
    if (day == " ") {
    	var days = ((month == " ") ? 31 : daysInMonth(year, month));
        	
    	objDay.options.length = 0;
        objDay.options[0] = new Option("Choose a day", " ");
        	
        for (var i = 1; i <= days; i++) {
        	objDay.options[i] = new Option(i, i);
        }	
    }
}
 
function setDay() {
	var year = document.getElementById("year");
    var month = document.getElementById("month");
    var day = document.getElementById("day");
    setDayList(year, month,day);
}

function setType() {
	var objType = document.getElementById("revenueType");
	var objMonth = document.getElementById("month");
	var objDay = document.getElementById("day");
	var objYear = document.getElementById("year");
	
	var value = objType.options[objType.selectedIndex].value;
	
	if(value == "get-day-revenue") {
		objDay.style.display = "";
		objMonth.style.display = "";
		objYear.style.display = "";
	}
	else if(value == "get-month-revenue") {
		objDay.style.display = "none";
		objMonth.style.display = "";
		objYear.style.display = "";
	}
	else if(value == "get-total-revenue") {
		objDay.style.display = "none";
		objMonth.style.display = "none";
		objYear.style.display = "none";
	}
	else if(value == "get-top-selling") {
		objDay.style.display = "none";
		objMonth.style.display = "none";
		objYear.style.display = "none";
	}
}