

function apiFetchAllbookings(table) {
        const userId = sessionStorage.getItem("userId");
        console.log(userId)
        const url = `http://localhost:8080/user/getcurrentbookings/${userId}`
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
    const table = document.getElementById('allUserBooking')

    apiFetchAllbookings(table)
}

setupTable()



function propulateActualData(table, bd) {

    for(const booking of bd) {

        const userId = sessionStorage.getItem("userId");

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
        row.insertCell(9).innerHTML = "confirmed" +` <button type="button" class="btn btn-secondary" onclick='showConfirmDeleteModal(${userId},${booking.bookingId})' style="margin-left:30px;font-size: 15px;font-weight: 600;font-family: sans-serif;" data-bs-dismiss="modal">Cancel</button>`


    }
}

function showConfirmDeleteModal(userId,bookingId) {
    console.log('clicked ' + bookingId)
    const myModalEl = document.getElementById('deleteModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()

    const btDl = document.getElementById('btDl')
    btDl.onclick = () => {
        apiCallDeleteBooking(userId,bookingId, modal)
        
        window.location.reload()
        
    }
}



function apiCallDeleteBooking(userId,bookingId, modal) {
    
    const url = `http://localhost:8080/user/${userId}/booking/${bookingId}`

    axios.delete(url)
        .then(res => res.data) // you converted complete response in to our business reponse
        // .then( data => console.log(data.msg) ) // this line can be written in destructured form as below
        .then( ({ sts, msg, bd }) =>  modal.hide())
        .catch(console.log)
}