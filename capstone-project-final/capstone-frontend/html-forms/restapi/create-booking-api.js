
const validateForm = ({ price, ttimeTAke }) => {

    if (price <=0) return { msg: 'invalid price', sts: false}
    if (ttimeTAke <=0) return { msg: 'invalid total time', sts: false}

    return { sts : 'success', msg :'all fields are valid' }
}

function createNewBooking(booking, form)
{
    const headers = {
        'content-type' : 'application/json'
    }


    axios.post('http://localhost:8080/booking/createNewBookings', booking, { headers })
         .then(res => {

            form.reset()
            showSuccessModal()
            
         })

         .catch(err => console.log(err))
}

function setupForm()
{

    const err = document.getElementById('errMsg')
    err.style.display = 'none'

    
    const formCreateBooking= document.getElementById('formCreateBooking')

    formCreateBooking.onsubmit = ev => {
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

        const booking = Object.fromEntries(formData.entries())
        console.log(booking)

        const { sts, msg } = validateForm(booking)

        if (sts) createNewBooking(booking, formCreateBooking)
        else {
            err.style.display = 'block'
            err.innerHTML = `<strong>${msg}</strong>`
        }


        
    }
}

setupForm()

function showSuccessModal() {
    const myModalEl = document.getElementById('successModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}