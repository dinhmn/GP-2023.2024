export default function render() {
  let localstorage = window.localStorage;
  document.getElementById("cart").innerHTML = localstorage.getItem('order') !== null 
  ? JSON.parse(localstorage.getItem('order')).length 
  : 0;
}
