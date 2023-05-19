const validateForm = ({ comment, rating}) => {

    if (comment.length <= 0) return { msg: 'Comment cannot be empty', sts: false}
    if (rating.length <= 0) return { msg: 'Rating cannot be empty', sts: false}
 
    return { sts : true , msg :'all fields are valid' }
}

function setupForm() {

    const err = document.getElementById('errMsg')
    err.style.display = 'none'
    
    const feedback = document.getElementById('feedback')
    console.log(feedback)

    feedback.onsubmit = ev => { 

        ev.preventDefault() 

        const formData = new FormData(ev.target) 

        const user = Object.fromEntries(formData.entries()) 
        console.log(user)

        // apiFeedback(user, feedback)

        const { sts, msg } = validateForm(user)

        if (sts) apiFeedback(user, feedback)
        else {
            err.style.display = 'block'
            err.innerHTML = `<strong>${msg}</strong>`
        }
    }
}

setupForm()

function apiFeedback(user, form) {
    const headers = {
        'content-type': 'application/json'
    }

    const userId = sessionStorage.getItem("userId");
    console.log(userId)
    const url = `http://localhost:8080/user/${userId}/feedback`
    axios.post(url , user, { headers })
        .then(()=> {
            form.reset()
            showSuccessModal()

        }).catch(err => console.log(err))
}

function showSuccessModal() {
    const myModalEl = document.getElementById('successModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}

function goBack() {
    window.history.back();
}
function reloadPage() {
    location.reload();
  }