// Funcția principală pentru adăugarea în coș
function addToCart(form) {
    const isAuthenticated = document.body.getAttribute("data-authenticated") === "true";
    if (!isAuthenticated) {
        showTemporaryMessageError();
        return;
    }

    // Verifică input-ul cantității
    var quantityInput = form.querySelector('input[name="quantity"]');
    if (!quantityInput) {
        alert('Quantity input not found');
        return;
    }

    var quantity = quantityInput.value;
    var productId = form.querySelector('button').getAttribute('data-id');

    // Trimite cererea AJAX
    $.ajax({
        url: '/addToCart/' + productId,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ productId: productId, quantity: quantity }),
        success: function(response) {
            // Afișează mesajul de succes
            var message = document.getElementById('temporaryMessage');
            message.style.display = 'block';
            message.style.opacity = 1;

            setTimeout(function() {
                message.style.opacity = 0;
                setTimeout(function() {
                    message.style.display = 'none';
                }, 500);
            }, 1000);
        }
    });
}

// Funcția pentru afișarea mesajului de eroare (utilizator neautentificat)
function showTemporaryMessageError() {
    const errorMessage = document.getElementById('temporaryMessageError');
    if (errorMessage) {
        errorMessage.style.display = 'flex';  // Schimbăm display-ul pentru a arăta overlay-ul
        errorMessage.style.opacity = 1;

        setTimeout(function() {
            errorMessage.style.opacity = 0;
            setTimeout(function() {
                errorMessage.style.display = 'none';  // Ascundem după 3.5 secunde
            }, 500);
        }, 3500);
    }
}

// Prevenirea introducerii unor valori mai mici decât 1
const quantityInput = document.getElementById('quantity-input');
if (quantityInput) {
    quantityInput.addEventListener('input', function() {
        if (parseInt(quantityInput.value) < 1) {
            quantityInput.value = 1;
        }
    });
}

// Închide alerta manual
function dismissAlert() {
    const alert = document.getElementById('temporaryMessageError');
    if (alert) {
        alert.style.display = 'none';
    }
}

// Setează un timeout pentru închiderea automată a erorii
setTimeout(() => {
    dismissAlert();
}, 3500);
