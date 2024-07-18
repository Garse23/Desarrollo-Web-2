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
            let url = 'http://localhost:8080/CampoVerde(11)/ProcesarVenta'

            return fetch(url, {
                method: 'post',
                headers: {
                    'content-type': 'application/json'
                },
                body: JSON.stringify({
                    detalles: detalles
                })
            }).then(response => {
                if (response.ok) {
                    // Si la solicitud fue exitosa, redirigir al índice
                    window.location.href = 'index.jsp';
                } else {
                    // Manejar errores si la solicitud no fue exitosa
                    console.error('Error en la solicitud:', response.statusText);
                }
            })
                    .catch(error => {
                        // Manejar cualquier otro error
                        console.error('Error en la solicitud:', error);
                    });
        });
    },

    onCancel: function (data) {
        alert("Pago cancelado");
        console.log(data);
    }
}).render('#paypal-button-conteiner');
