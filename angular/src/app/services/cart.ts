export class Item {
    public itemId: string;
    public name: string;
    public price: number;
  
    constructor(itemId: string, name: string, price: number) {
      this.itemId = itemId;
      this.name = name;
      this.price = price;
    }
  }
  
  export class Cart {
    private items: Item[];
  
    constructor() {
      this.items = [];
    }
  
    public addItem(item: Item): void {
      this.items.push(item);
    }
  
    public removeItem(itemId: string): boolean {
      const index = this.items.findIndex(item => item.itemId === itemId);
      if (index !== -1) {
        this.items.splice(index, 1);
        return true;
      }
      return false;
    }
  
    public getItems(): Item[] {
      return this.items;
    }
  
    public setItems(items: Item[]): void {
      this.items = items;
    }
  
    public clearCart(): void {
      this.items = [];
    }
  }
 