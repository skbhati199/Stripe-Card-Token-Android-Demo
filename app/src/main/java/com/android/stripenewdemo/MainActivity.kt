package com.android.stripenewdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.stripe.android.Stripe
import com.stripe.android.TokenCallback
import com.stripe.android.model.Card
import com.stripe.android.model.Token
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var stripe: Stripe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        stripe = Stripe(this, "")
        stripe = Stripe(this, BuildConfig.API_KEY)

        add_cart_button.setOnClickListener {

            if (card_layout.validateAllFields()) {
                var card: Card = card_layout.card!!
                stripe.createToken(
                        card,
                        BuildConfig.API_KEY,
                        object : TokenCallback {
                            override fun onSuccess(token: Token) {
                                Log.d("Card token", token.id.toString())
                                Log.d(
                                        "Card_Details",
                                        card.number + " " + card.expMonth.toString() + "/" + card.expYear.toString()
                                                + " " + card.cvc.toString() + " " + token.id + " " + card.name + " " + card.brand!!
                                )

                            }

                            override fun onError(error: Exception) {
                                Log.d("Stripe", error.localizedMessage)
                            }
                        })
            } else {
                Log.e("Stripe", "onCreate:  error")
            }
        }
    }
}


