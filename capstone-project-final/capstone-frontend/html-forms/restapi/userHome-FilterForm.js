
function handleSubmit(){

    const bookingFrom=document.getElementById('bookingFrom').value

    console.log(bookingFrom)
    const bookingDestination=document.getElementById('bookingDestination').value

    console.log(bookingDestination)
    const date=document.getElementById('date').value

    console.log(date)
}

function handleBookingFrom(){

    const bookingFrom=document.getElementById('bookingFrom').value

    console.log(bookingFrom)
    const bookingDestination=document.getElementById('bookingDestination').value

    console.log(bookingDestination)
    const date=document.getElementById('date').value

    console.log(date)
}

function handleDestination(){

    const bookingFrom=document.getElementById('bookingFrom').value

    console.log(bookingFrom)
    const bookingDestination=document.getElementById('bookingDestination').value

    console.log(bookingDestination)
    const date=document.getElementById('date').value

    console.log(date)
}


function setupTable() {
    const table = document.getElementById('tableUser')

    apiFetchAllBookings(table)
}

setupTable()

function propulateActualData(table, bookings) {

    for(const booking of bookings) {

        const { bookingId, bookingVname, bookingFrom, bookingDestination,date } = booking

        const viewPageUrl = `./view-more-user.html?bookingId=${bookingId}`


        const row = table.insertRow()
        row.insertCell(0).innerHTML = bookingId
        row.insertCell(1).innerHTML = bookingVname
        row.insertCell(2).innerHTML = bookingFrom
        row.insertCell(3).innerHTML = bookingDestination
        row.insertCell(4).innerHTML = date
        row.insertCell(5).innerHTML = `
        <a href='${viewPageUrl}'>View</a> 
        <a class='ms-2' href='#'>Comfirm</a>`
    }
}

function apiFetchAllBookings(table) {
    axios.get('http://localhost:8080/booking/list')
        .then(res => {
            const { data } = res
            console.log(data)  
            const { sts, msg, bd } = data

            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
        
}

function apiFetchAllCustomerInvoices(table, bookingId) {
    const url = `http://localhost:8080/invoice/customer/${bookingId}`
    axios.get(url)
        .then(res => {
            const { data } = res
            console.log(data)  
            const { sts, msg, bd } = data

            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}


