

function apiGetBookingDetails() {
   
    const id = localStorage.getItem("userId");

    axios.get(`http://localhost:8080/user/userdetails/${id}`)
        .then(httpReponse => httpReponse.data)
        .then(data => populateForm(document.getElementById('updateProfile'), data.bd))
        .catch(err => console.log(err))

        
}

function apiUpdateExistingForm(user, form) {

    axios.put('http://localhost:8080/user/updateUser', user)
        .then(httpResponse => httpResponse.data)
        .then(data => console.log(data.msg))
        .catch(err => console.log(err))

        showSuccessModal();
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

function setupForm() {

    const updateProfile = document.getElementById('updateProfile')

    updateProfile.onsubmit = ev => { // when form is submitted, this function would be called
        const formData = new FormData(ev.target)

        ev.preventDefault() // stop the default behaviour of refreshing the page
        console.log(ev)

        const rawData = Object.fromEntries(formData.entries()) // you are converting form data to js object
        console.log(rawData)

        const id = localStorage.getItem("userId");

        // if we need to update, we need to send id, so we creatd new object inclusive of id
        // const invoice = { 
        //     id : id, 
        //     client: rawData.client,
        //     invDt: rawData.invDt,
        //     amt : rawData.amt
        // }

         // ... spread operator, upack operator, it introduced in es6
        const user = { ...rawData, id }
        console.log(user)


        apiUpdateExistingForm(user, updateProfile)


         // we are pass form object to reset the form on success
        
    }
}

setupForm()

apiGetBookingDetails()

function showSuccessModal() {
    const myModalEl = document.getElementById('successModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}