/**13位时间戳转换成 年月日 上午 时间  2018-05-23 10:41:08 */
function createTime(v){
    if (v == null) {
        return "";
    }
    return new Date(parseInt(v)).toLocaleString()
}
/**重写toLocaleString方法*/
Date.prototype.toLocaleString = function() {
    var y = this.getFullYear();
    var m = this.getMonth()+1;
    m = m<10?'0'+m:m;
    var d = this.getDate();
    d = d<10?("0"+d):d;
    var h = this.getHours();
    h = h<10?("0"+h):h;
    var M = this.getMinutes();
    M = M<10?("0"+M):M;
    var S=this.getSeconds();
    S=S<10?("0"+S):S;
    return y+"-"+m+"-"+d+" "+h+":"+M+":"+S;
};