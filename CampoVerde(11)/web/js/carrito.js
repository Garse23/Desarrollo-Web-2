paypal.Buttons({
    style: {
        shape: 'pill',
        label: 'pay'
    },
    createOrder: function (data, actions) {
        return actions.order.create({
            purchase_units: [{
                    amount: {
                        value: total.toFixed(2)
                    }
                }]
        });
    },

    onApprove: function (data, actions) {
        actions.order.capture().then(function (detalles) {
            console.log(detalles);
            let url = 'http://localhost:8080/CampoVerde(10)/ProcesarVenta'

             return fetch(url,{
                method:'post',
                headers:{
                    'content-type': 'application/json'
                },
                body: JSON.stringify({
                    detalles: detalles
                })
            })
        });
    },

    onCancel: function (data) {
        alert("Pago cancelado");
        console.log(data);
    }
}).render('#paypal-button-conteiner');
