/*
 * [encrypt 加密]
 *
 * 进行网络传输需要进行  encodeURIComponent(data) 操作
 */
function encrypt(word, key){
    var srcs = CryptoJS.enc.Utf8.parse(word);

    var encrypted = CryptoJS.AES.encrypt(srcs, key, {
        //iv: CryptoJS.enc.Utf8.parse("0102030405060708"), //0102030405060708偏移量，16位
        mode:CryptoJS.mode.ECB, //aes加密模式 ecb
        padding: CryptoJS.pad.Pkcs7 //填充
    });

    //把object转化为string
    return encrypted.toString();
}


/**
 * [decrypt 解密]
 * @return {[type]} [description]
 */
function decrypt(encrypted, key){
    var decrypted = CryptoJS.AES.decrypt(encrypted, key, {
        //iv: CryptoJS.enc.Utf8.parse("0102030405060708"),
        mode:CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });

    return CryptoJS.enc.Utf8.stringify(decrypted).toString();
}