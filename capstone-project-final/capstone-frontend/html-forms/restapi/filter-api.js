
function logOut() {
    sessionStorage.setItem("userId", null)
    sessionStorage.setItem("role", null)
    window.location.href = "../html-forms/login-ac.html"
}

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
    else
    {
        axios.get(`http://localhost:8080/booking/filterByFDT/${bookingFrom}/${bookingDestination}/${date}`)
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

        const viewPageUrl = `./view-more-user.html?bookingId=${bookingId}`

        const CurrentDate = new Date();

        const date1 = new Date(booking.date);

        if(date1>=CurrentDate)
        {

        const row = table.insertRow()
        row.insertCell(0).innerHTML = bookingId
        row.insertCell(1).innerHTML = bookingVname
        row.insertCell(2).innerHTML = bookingFrom
        row.insertCell(3).innerHTML = bookingDestination
        row.insertCell(4).innerHTML = date
        row.insertCell(5).innerHTML = `
        <button type="button" class="btn btn-secondary" onclick="window.location='${viewPageUrl}';" data-bs-dismiss="modal">View</a></button>
        <button type="button" class="btn btn-secondary" onclick='confirmBooking(${bookingId})';" data-bs-dismiss="modal">Confirm</a></button>`
        }
    } 
    }

setupTable()


function confirmBooking(bookingId) {
    
    const userId = sessionStorage.getItem("userId");

    console.log(userId)
    console.log(bookingId)

    const headers = {
        'content-type': 'application/json'
    }
    axios.post(`http://localhost:8080/user/${userId}/userbookings/${bookingId}`, { headers })
          
        .then(res => {
            showSuccessModalEventBook()
        }).catch(err => console.log(err))
}



function showSuccessModalEventBook() {
    const myModalEl = document.getElementById('successModalbooking');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}