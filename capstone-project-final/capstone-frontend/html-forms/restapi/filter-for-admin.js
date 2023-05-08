
function apiGetBookingDetails(table) {

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const bookingFrom = urlParams.get('bookingFrom');
    const bookingDestination = urlParams.get('bookingDestination');
    const date = urlParams.get('date');

    console.log(bookingFrom);
    console.log(bookingDestination);
    console.log(date);

    if(bookingFrom==null & date==null)
    {
        axios.get(`http://localhost:8080/booking/filterByDestination/${bookingDestination}`)
            .then(res => {
                   const { data } = res
                   console.log(data)  
                   const { sts, msg, bd } = data

                 propulateActualData(table, bd)
            })
        .catch(err => console.log(err))


    }
    else if(bookingDestination==null & date==null)
    {
        axios.get(`http://localhost:8080/booking/filterByFrom/${bookingFrom}`)
            .then(res => {
                   const { data } = res
                   console.log(data)  
                   const { bd } = data

                 propulateActualData(table, bd)
            })
        .catch(err => console.log(err))


    }

    else if(bookingDestination==null & bookingFrom==null)
    {
        axios.get(`http://localhost:8080/booking/filterByDate/${date}`)
            .then(res => {
                   const { data } = res
                   console.log(data)  
                   const { sts, msg, bd } = data

                 propulateActualData(table, bd)
            })
        .catch(err => console.log(err))


    }

        
}

function setupTable() {

    const table = document.getElementById('tableFilter')

     apiGetBookingDetails(table)
}

function propulateActualData(table, bookings) {

    for(const booking of bookings) {

        const { bookingId, bookingVname, bookingFrom, bookingDestination,date } = booking

        const updatePageUrl = `./update-booking-slot.html?bookingId=${bookingId}`
        const viewPageUrl = `./view-more-booking.html?bookingId=${bookingId}`


        const row = table.insertRow()
        row.insertCell(0).innerHTML = bookingId
        row.insertCell(1).innerHTML = bookingVname
        row.insertCell(2).innerHTML = bookingFrom
        row.insertCell(3).innerHTML = bookingDestination
        row.insertCell(4).innerHTML = date
        row.insertCell(5).innerHTML = `
        <a href='${viewPageUrl}'>View</a> 
        <a class='ms-2' href='${updatePageUrl}'>Update</a> 
        <a class='ms-2' href='#' onclick='showConfirmDeleteModal(${bookingId})'>Delete</a>`
    } 
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
        
        window.location.reload()
        
    }
}

function apiCallDeleteBooking(bookingId, modal) {
    const url = `http://localhost:8080/booking/delete/${bookingId}`

    axios.delete(url)
        .then(res => res.data) // you converted complete response in to our business reponse
        // .then( data => console.log(data.msg) ) // this line can be written in destructured form as below
        .then( ({ sts, msg, bd }) =>  modal.hide())
        .catch(console.log)
}