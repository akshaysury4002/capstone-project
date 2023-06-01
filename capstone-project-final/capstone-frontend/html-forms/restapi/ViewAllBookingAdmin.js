function setupTable() {
    const table = document.getElementById('AllUserBookings')

    // const btnSearch = document.getElementById('btnSearch')
    
    // btnSearch.onclick = () =>   {

    //     const api=apiFetchBooking(table, document.getElementById('txtUsername').value )
    //     console.log(api)
    // }
    
    const userId = sessionStorage.getItem("userId");
    if(userId!='null')
    {
    apiFetchAllbookings(table)
    }
}

 setupTable()


function propulateActualData(table, bd) {
    
    for(const userBooking of bd) {
        console.log(userBooking)
        const {bookingDestination, bookingFrom,date, price, time, uname, userId } = userBooking 

        console.log(userBooking)
        const row = table.insertRow()
        row.insertCell(0).innerHTML = userId
        row.insertCell(1).innerHTML = uname
        row.insertCell(2).innerHTML = bookingFrom
        row.insertCell(3).innerHTML = bookingDestination
        row.insertCell(4).innerHTML = date
        row.insertCell(5).innerHTML = time
        row.insertCell(6).innerHTML = price
        row.insertCell(7).innerHTML = "done"
        console.log(userBooking)
            
            
            
        
    }
}



function apiFetchAllbookings(table) {
    axios.get('http://localhost:8080/user/alluserbookings')
        .then(res => {
           
            const { data } = res
             
            const { sts, msg, bd } = data

            console.log(bd)
            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}