    createEmptyTable(10);

    document.querySelector("#select_city").addEventListener("change", () => {
        var stationId = document.getElementById("select_city").value;
        refreshTable();
        getDataAndCreateTable(stationId);
    });

    function getDataAndCreateTable(stationId) {
        const xhr = new XMLHttpRequest();
        var url = window.location;

        xhr.open('GET', url + "api/" + stationId, true);
        xhr.send();

        xhr.onloadstart = () => {

        }

        xhr.onload = () => {
            if(xhr.status === 200) {
                var result = JSON.parse(xhr.response);
                fillTableCells(result);
            } else {
                console.log("Something went wrong: " + xhr.status + ". Unable to load data ");
                alert("Something went wrong: " + xhr.status + ". Unable to load data");
            }
        }
    }


    function fillTableCells(data) {
        //date + time (first column)
        fillTableCellDateAndTime(data);

        //data (other columns)
        let airQualityIndex = new AirQualityIndex();
        for(var i = 0; i < data.length; i++) {
            for(var j = 0; j < data[i].values.length; j++) {
                var idSelector = ("#" + data[i].key) + j;
                var tableCell = document.querySelector(idSelector);

                tableCell.childNodes[0].nodeValue = data[i].values[j].value;
                tableCell.className = (airQualityIndex.assignIndex(data[i].key, data[i].values[j].value));
            }
        }
    }

    function fillTableCellDateAndTime(data) {
        if(data.length > 0) {
            for(var i = 0; i < data[0].values.length; i++) {
                var idSelector = "#date"+ i;
                var tableCell = document.querySelector(idSelector);
                tableCell.childNodes[0].nodeValue = data[0].values[i].date;
                //set cell class
                tableCell.className = "date";
            }
        }
    }

    function refreshTable() {
        removeTableBody();
        createEmptyTable(10);
    }

    function removeTableBody(){
        $("#mainTable #tbl-row").remove();
    }

    function createEmptyTable(rows){
        var tbl  = document.querySelector('#mainTable');

        for(var i = 0; i < rows; i++){
            var tr = tbl.insertRow();
            tr.setAttribute("id", "tbl-row");
            for(var j = 0; j < 8; j++){
                var td = tr.insertCell();
                td.appendChild(document.createTextNode('b.d'));
                td.style.border = '1px solid black';
                td.classList.add("no-data");
                if(j == 0) { td.setAttribute("id", ("date" + i)); }
                else if (j == 1) { td.setAttribute("id", ("CO" + i)); }
                else if (j == 2) { td.setAttribute("id", ("NO2" + i)); }
                else if (j == 3) { td.setAttribute("id", ("O3" + i)); }
                else if (j == 4) { td.setAttribute("id", ("PM10" + i)); }
                else if (j == 5) { td.setAttribute("id", ("SO2" + i)); }
                else if (j == 6) { td.setAttribute("id", ("C6H6" + i)); }
                else if (j == 7) { td.setAttribute("id", ("PM2" + i)); }
            }
        }
    }

    class AirQualityIndex {

        assignIndex(key, value) {
            if (key == "CO") {
                if (value >= 0 && value <= 3000) {
                    return "very-good";
                } else if(value >= 3001 && value <= 7000) {
                    return "good";
                }  else if(value >= 7001 && value <= 11000) {
                    return "moderate";
                }  else if(value >= 11001 && value <= 15000) {
                    return "sufficient";
                }  else if(value >= 15001 && value <= 21000) {
                    return "bad";
                }  else if(value > 21000) {
                    return "very-bad";
                }
            } else if(key == "C6H6") {
                if (value >= 0 && value <= 6) {
                    return "very-good";
                } else if(value >= 6.1 && value <= 11) {
                    return "good";
                }  else if(value >= 11.1 && value <= 16) {
                    return "moderate";
                }  else if(value >= 16.1 && value <= 21) {
                    return "sufficient";
                }  else if(value >= 21.1 && value <= 51) {
                    return "bad";
                }  else if(value > 51) {
                    return "very-bad";
                }
            } else if(key == "SO2") {
                if (value >= 0 && value <= 50) {
                    return "very-good";
                } else if(value >= 50.1 && value <= 100) {
                    return "good";
                }  else if(value >= 100.1 && value <= 200) {
                    return "moderate";
                }  else if(value >= 200.1 && value <= 350) {
                    return "sufficient";
                }  else if(value >= 350.1 && value <= 500) {
                    return "bad";
                }  else if(value > 500) {
                    return "very-bad";
                }
            }  else if(key == "NO2") {
                if (value >= 0 && value <= 40) {
                    return "very-good";
                } else if(value >= 40.1 && value <= 100) {
                    return "good";
                }  else if(value >= 100.1 && value <= 150) {
                    return "moderate";
                }  else if(value >= 150.1 && value <= 200) {
                    return "sufficient";
                }  else if(value >= 200.1 && value <= 400) {
                    return "bad";
                }  else if(value > 400) {
                    return "very-bad";
                }
            } else if(key == "O3") {
                if (value >= 0 && value <= 70) {
                    return "very-good";
                } else if(value >= 70.1 && value <= 120) {
                    return "good";
                }  else if(value >= 120.1 && value <= 150) {
                    return "moderate";
                }  else if(value >= 150.1 && value <= 180) {
                    return "sufficient";
                }  else if(value >= 180.1 && value <= 240) {
                    return "bad";
                }  else if(value > 240) {
                    return "very-bad";
                }
            } else if(key == "PM2") {
                if (value >= 0 && value <= 13) {
                    return "very-good";
                } else if(value >= 13.1 && value <= 35) {
                    return "good";
                }  else if(value >= 35.1 && value <= 55) {
                    return "moderate";
                }  else if(value >= 55.1 && value <= 75) {
                    return "sufficient";
                }  else if(value >= 75.1 && value <= 110) {
                    return "bad";
                }  else if(value > 110) {
                    return "very-bad";
                }
            } else if(key == "PM10") {
                if (value >= 0 && value <= 20) {
                    return "very-good";
                } else if(value >= 20.1 && value <= 50) {
                    return "good";
                }  else if(value >= 50.1 && value <= 80) {
                    return "moderate";
                }  else if(value >= 80.1 && value <= 110) {
                    return "sufficient";
                }  else if(value >= 110.1 && value <= 150) {
                    return "bad";
                }  else if(value > 150) {
                    return "very-bad";
                }
            }
            return "no-data";
        }
    }