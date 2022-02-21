

function yesnoCheck() {
    if (document.getElementById('yesCheck').checked) {
        document.getElementById('ifYes').style.visibility = 'visible';
        const imageFile = document.getElementById("image");

        imageFile.onchange = function () {
            const maxAllowedSize = 300 * 1024;
            if (this.files[0].size > maxAllowedSize) {
                alert("Image File is too big!");
                this.value = "";
            }
            if (this.files[0]==null|| this.files[0].value===0) {
                alert("upload image!");
                this.value = "";
            }
        }
    } else {
        document.getElementById('ifYes').style.visibility = 'hidden';
    }
}
/*
const imageFile = document.getElementById("image");

imageFile.onchange = function () {
    const maxAllowedSize = 300 * 1024;
    if (this.files[0].size > maxAllowedSize) {
        alert("Image File is too big!");
        this.value = "";
    }
    if (this.files[0]==null|| this.files[0].value===0) {
        alert("upload image!");
        this.value = "";
    }
}
function yesnoCheck() {
    if (document.getElementById('yesCheck').checked) {
        document.getElementById('ifYes').style.visibility = 'visible';
    } else {
        document.getElementById('ifYes').style.visibility = 'hidden';
    }
}*/
