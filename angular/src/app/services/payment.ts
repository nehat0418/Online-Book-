export class Payment {
    public paymentId: string;
    public paymentMethod: string;
    public amount: number;
    public cardNumber: string;
    public CVV: string;
    public expirationDate: string;
    public status: string;
  
    constructor(
      paymentId: string,
      paymentMethod: string,
      amount: number,
      cardNumber: string,
      CVV: string,
      expirationDate: string,
      status: string
    ) {
      this.paymentId = paymentId;
      this.paymentMethod = paymentMethod;
      this.amount = amount;
      this.cardNumber = cardNumber;
      this.CVV = CVV;
      this.expirationDate = expirationDate;
      this.status = status;
    }
  
    
    }
  
  