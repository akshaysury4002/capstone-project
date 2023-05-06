


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

setupTable()

