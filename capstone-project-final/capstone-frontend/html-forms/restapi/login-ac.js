
const readIdQueryParam = () => {
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });
    return params.id
}

function apiGetInvoiceDetails() {
    const id = readIdQueryParam()

    axios.get(`http://localhost:8080/user/${id}`)
        .then(httpReponse => httpReponse.data)
        .then(data => populateTableDetails(data.bd))
        .catch(err => console.log(err))
}

function populateDetails({ id, uname, email, password }) {
    // populating invoice details without table
    document.getElementById("id").innerHTML = `<strong> id </strong> : ${id}`
    document.getElementById("uname").innerHTML = `<strong> uname </strong> : ${uname}`
    document.getElementById("email").innerHTML = `<strong> email </strong> : ${email}`
    document.getElementById("password").innerHTML = `<strong> password </strong> : ${password}`
}

function populateTableDetails({ id, uname, email, password }) {
    // populating invoice details inside a table
    const table = document.getElementById('tableDetails')
    const row = table.insertRow()
    row.insertCell(0).innerHTML = id
    row.insertCell(1).innerHTML = uname
    row.insertCell(2).innerHTML = email
    row.insertCell(3).innerHTML = password 
}

apiGetInvoiceDetails()