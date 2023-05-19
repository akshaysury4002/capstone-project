

function apiGetBookingDetails() {
    const userId = sessionStorage.getItem("userId");

    axios.get(`http://localhost:8080/user/userdetails/${userId}`)
        .then(httpReponse => httpReponse.data)
        .then(data => populateForm(document.getElementById('MyProfile'), data.bd))
        .catch(err => console.log(err))

        
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



apiGetBookingDetails()