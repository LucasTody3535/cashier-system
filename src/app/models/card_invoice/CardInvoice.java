package app.models.card_invoice;

import java.util.ArrayList;

import app.enums.payment_type.PaymentType;
import app.models.card.Card;
import app.models.invoice.Invoice;
import app.models.product.Product;

public class CardInvoice extends Invoice {
	private Card card;

	public CardInvoice(long id, ArrayList<Product> products, float total, String doc, String pdv, String supermarket,
			PaymentType paymentType, Card card) {
		super(id, products, total, doc, pdv, supermarket, paymentType);
		this.setCard(card);
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
}
