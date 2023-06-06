

function logOut() {
    sessionStorage.setItem("userId", null)
    sessionStorage.setItem("role", null)
    window.location.href = "../html-forms/login-ac.html"
}

const readIdQueryParam = () => {
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });
    return params.bookingId
}

console.log(readIdQueryParam())

function apiGetBookingDetails() {
    const bookingId = readIdQueryParam()

    axios.get(`http://localhost:8080/booking/getbookingbyid/${bookingId}`)
    .then(httpReponse => httpReponse.data)
    .then(data => populateForm(document.getElementById('viewMore'), data.bd))
    .catch(err => console.log(err))

        
}

function setupTable() {

     apiGetBookingDetails()
}



function populateForm(form, data) {
        console.log(data)
        const { elements } = form; // it will give all input elements 
        console.log(elements)
    
        const entries = Object.entries(data) // it will give all [keys, values] of invoice json which is received from server
        console.log(entries)
    
        for (const entry of entries) {
            // here entry is an array of 2 elements, oth is key, first is value
            console.log(entry)
            // const key = entry[0]
            // const value = entry[1]
    
            const [key, value] = entry
            const inputField = elements.namedItem(key)
            console.log(inputField)
            if (inputField) inputField.value = value
        }

    
 }


setupTable()


function confirmBooking() {
    const bookingId = readIdQueryParam()
    const userId = sessionStorage.getItem("userId");

    console.log(userId)
    console.log(bookingId)

    const headers = {
        'content-type': 'application/json'
    }
    axios.post(`http://localhost:8080/user/${userId}/userbookings/${bookingId}`, { headers })
          
        .then(res => {
            showSuccessModalBook()
        }).catch(err => console.log(err))
}



function showSuccessModalBook() {
    const myModalEl = document.getElementById('successModalbooking');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}