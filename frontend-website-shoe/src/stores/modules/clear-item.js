export default function clear() {
  let localstorage = window.localStorage;
  let cartItemList = document.querySelectorAll("#cart-item > div");
  let totalPrice = 0;
  document.getElementById("cart").innerHTML = localstorage.getItem('order') !== null 
  ? JSON.parse(localstorage.getItem('order')).length 
  : 0;
  if (localstorage.getItem('order') !== null) {
    let objectItem = JSON.parse(localstorage.getItem('order'));
    for (let index = 0; index < objectItem.length; index++) {
      const element = objectItem[index];
      let price = element.productPriceSale !== null ? element.productPriceSale : element.productPrice;
      totalPrice += Number(price) * Number(element.productQuantity);
    }
  }
  for (let index = cartItemList.length - 2; index >= 0; index--) {
    cartItemList[index].parentNode.removeChild(cartItemList[index]);
  }
  document.querySelector(".temporaryCalculation").innerText = ((totalPrice / 1).toFixed(0).replace('.', ',')).toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.') + 'đ'  
  document.querySelector(".temporaryCalculation-1").innerText = ((totalPrice / 1).toFixed(0).replace('.', ',')).toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.') + 'đ'  
  document.querySelector(".temporaryCalculation-2").innerText = ((totalPrice / 1).toFixed(0).replace('.', ',')).toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.') + 'đ'  

}
