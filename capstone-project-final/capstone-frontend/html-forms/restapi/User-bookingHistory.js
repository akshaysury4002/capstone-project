

function apiFetchAllbookings(table) {
    const userId = localStorage.getItem("userId");
    console.log(userId)
    const url = `http://localhost:8080/user/getbookinghistory/${userId}`
    axios.get(url)
        .then(res => {
            const { data } = res
            console.log(data)  
            const { sts, msg, bd } = data
            console.log(bd)
            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}



function setupTable() {
    const table = document.getElementById('UserBookingHistory')

    apiFetchAllbookings(table)
}

setupTable()

function propulateActualData(table, bd) {

    for(const booking of bd) {

        const row = table.insertRow()
        row.insertCell(0).innerHTML = booking.bookingId
        row.insertCell(1).innerHTML = booking.bookingVname
        row.insertCell(2).innerHTML = booking.bookingFrom
        row.insertCell(3).innerHTML = booking.bookingDestination
        row.insertCell(4).innerHTML = booking.date
        row.insertCell(5).innerHTML = booking.time
        row.insertCell(6).innerHTML = booking.typeVahi
        row.insertCell(7).innerHTML = booking.ttimeTAke
        row.insertCell(8).innerHTML = booking.price
        row.insertCell(9).innerHTML = "completed"

    }
}

