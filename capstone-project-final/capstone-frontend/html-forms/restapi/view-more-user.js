const readIdQueryParam = () => {
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });
    return params.bookingId
}

console.log(readIdQueryParam())

function apiGetBookingDetails(table) {
    const bookingId = readIdQueryParam()

    axios.get(`http://localhost:8080/booking/getbookingbyid/${bookingId}`)
            .then(res => {
                   const { data } = res
                   console.log(data)  
                   const { sts, msg, bd } = data

                 propulateActualData(table, bd)
            })
        .catch(err => console.log(err))

        
}

function setupTable() {
    const table = document.getElementById('tableViewMoreetalis')

     apiGetBookingDetails(table)
}

function propulateActualData(table, bd) {

        const row = table.insertRow()
        row.insertCell(0).innerHTML = bd.bookingId
        row.insertCell(1).innerHTML = bd.bookingVname
        row.insertCell(2).innerHTML = bd.bookingFrom
        row.insertCell(3).innerHTML = bd.bookingDestination
        row.insertCell(4).innerHTML = bd.date
        row.insertCell(5).innerHTML = bd.time
        row.insertCell(6).innerHTML = bd.typeVahi
        row.insertCell(7).innerHTML = bd.ttimeTAke
        row.insertCell(8).innerHTML = bd.price
        row.insertCell(9).innerHTML = ` <a href='#'>Confirm</a>` 
    }

setupTable()

function showConfirmDeleteModal(bookingId) {
    console.log('clicked ' + bookingId)
    const myModalEl = document.getElementById('deleteModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()

    const btDl = document.getElementById('btDl')
    btDl.onclick = () => {
        apiCallDeleteBooking(bookingId, modal)
        window.location.href = '../html-forms/Admin-home.html'
        
    }
}
