

function setupTable() {
    const table = document.getElementById('AllFeedback')

    
    apiFetchAllbookings(table)
}

 setupTable()


function propulateActualData(table, bd) {
    
    for(const feedback of bd) {
        
        const {id, comment,rating} = feedback 

       
        const row = table.insertRow()
        row.insertCell(0).innerHTML = id
        row.insertCell(1).innerHTML = comment
        row.insertCell(2).innerHTML = rating         
            
            
        
    }
}

function apiFetchAllbookings(table) {
    axios.get('http://localhost:8080/user/feedback')
        .then(res => {
           
            const { data } = res
             
            const { sts, msg, bd } = data

            console.log(bd)
            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}