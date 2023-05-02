export default function render() {
  let localstorage = window.localStorage;
  let totalPrice = 0;
  document.getElementById("cart").innerHTML = localstorage.getItem('order') !== null 
  ? JSON.parse(localstorage.getItem('order')).length 
  : 0;
  if (localstorage.getItem('order') !== null) {
    let objectItem = JSON.parse(localstorage.getItem('order'));
    for (let index = 0; index < objectItem.length; index++) {
      const element = objectItem[index];
      let price = element.productModel.productPriceSale !== null ? element.productModel.productPriceSale : element.productModel.productPrice;
      totalPrice += Number(price) * Number(element.productQuantity);
    }
  }
  document.getElementById("total-price-cart").innerHTML = ((totalPrice / 1).toFixed(0).replace('.', ',')).toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.') + 'đ'

}
