
function createNewUser(user, form)
{
    const headers = {
        'content-type' : 'application/json'
    }


    axios.post('http://localhost:8080/user/', user, { headers })
         .then(res => {

            form.reset()
            showSuccessModal()
         })

         .catch(err => console.log(err))
}

function setupForm()
{
    const formCreateUser= document.getElementById('formCreateUser')

    formCreateUser.onsubmit = ev => {
        ev.preventDefault()
        console.log(ev)

        // this is used for manually insert the date

        // const user = { id : 105, uname : 'pooja', email : 'pooja@gmail', password : 'pooja@123' }

        // this is used for insert the data manully dynamic 
        const formData = new FormData(ev.target)

        for(let key of formData.keys())
        {
            console.log(key)
        }

        for(let val of formData.values())
        {
            console.log(val)
        }

        const user = Object.fromEntries(formData.entries())
        console.log(user)


        createNewUser(user, formCreateUser)
    }
}

setupForm()

function showSuccessModal() {
    const myModalEl = document.getElementById('successModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}