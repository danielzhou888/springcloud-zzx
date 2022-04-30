// var encryptUtils = {
//     getAesString: function (data,key,iv) {//加密
//         var key  = CryptoJS.enc.Utf8.parse(key);
//         var iv   = CryptoJS.enc.Utf8.parse(iv);
//         var encrypted =CryptoJS.AES.encrypt(data,key,
//             {
//                 iv:iv,
//                 mode:CryptoJS.mode.CBC,
//                 padding:CryptoJS.pad.Pkcs7
//             });
//         return encrypted.toString();    //返回的是base64格式的密文
//     },
//
//     getDAesString: function(encrypted,key,iv){//解密
//         var key  = CryptoJS.enc.Utf8.parse(key);
//         var iv   = CryptoJS.enc.Utf8.parse(iv);
//         var decrypted =CryptoJS.AES.decrypt(encrypted,key,
//             {
//                 iv:iv,
//                 mode:CryptoJS.mode.CBC,
//                 padding:CryptoJS.pad.Pkcs7
//             });
//         return decrypted.toString(CryptoJS.enc.Utf8);
//     },
//     getAES: function(data){ //加密
//         var key  = '12345678';  //密钥
//         var iv   = '1234567812345678';
//         var encrypted =this.getAesString(data,key,iv); //密文
//         var encrypted1 =CryptoJS.enc.Utf8.parse(encrypted);
//         return encrypted;
//     },
//
//     getDAes: function(data){//解密
//         var key  = '12345678';  //密钥
//         var iv   = '1234567812345678';
//         var decryptedStr = this.getDAesString(data,key,iv);
//         return decryptedStr;
//     }
//
// }
//


function getAesString(data,key,iv){//加密
    var key  = CryptoJS.enc.Utf8.parse(key);
    var iv   = CryptoJS.enc.Utf8.parse(iv);
    var encrypted =CryptoJS.AES.encrypt(data,key,
        {
            iv:iv,
            mode:CryptoJS.mode.CBC,
            padding:CryptoJS.pad.Pkcs7
        });
    return encrypted.toString();    //返回的是base64格式的密文
}
function getDAesString(encrypted,key,iv){//解密
    var key  = CryptoJS.enc.Utf8.parse(key);
    var iv   = CryptoJS.enc.Utf8.parse(iv);
    var decrypted =CryptoJS.AES.decrypt(encrypted,key,
        {
            iv:iv,
            mode:CryptoJS.mode.CBC,
            padding:CryptoJS.pad.Pkcs7
        });
    return decrypted.toString(CryptoJS.enc.Utf8);
}

function getAES(data){ //加密
    var key  = '12345678';  //密钥
    var iv   = '1234567812345678';
    var encrypted =getAesString(data,key,iv); //密文
    var encrypted1 =CryptoJS.enc.Utf8.parse(encrypted);
    return encrypted;
}

function getDAes(data){//解密
    var key  = '12345678';  //密钥
    var iv   = '1234567812345678';
    var decryptedStr =getDAesString(data,key,iv);
    return decryptedStr;
}
