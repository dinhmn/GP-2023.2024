export default function render() {
  let localstorage = window.localStorage;
  let totalPrice = 0;
  document.getElementById("cart").innerHTML = localstorage.getItem('order') !== null 
  ? JSON.parse(localstorage.getItem('order')).value.length 
  : 0;
  if (localstorage.getItem('order') !== null) {
    let objectItem = JSON.parse(localstorage.getItem('order'));
    for (let index = 0; index < objectItem.value.length; index++) {
      const element = (objectItem.value)[index];
      let price = element.productPriceSale !== null ? element.productPriceSale : element.productPrice;
      totalPrice += Number(price) * Number(element.productQuantity);
    }
  }
  document.getElementById("total-price-cart").innerHTML = ((totalPrice / 1).toFixed(0).replace('.', ',')).toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.') + 'đ'  
  }
