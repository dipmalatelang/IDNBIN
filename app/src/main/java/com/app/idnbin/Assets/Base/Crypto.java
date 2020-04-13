package com.app.idnbin.Assets.Base;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Crypto {
    private List<String> time1 = Collections.singletonList("5:30-5:30");
    private List<String> multiplier1 = Collections.singletonList("x3x5");
    private List<String> multiplier2 = Collections.singletonList("x3");
    private List<String> multiplier3 = Collections.singletonList("x3x5x10");
    private List<String> multiplier4 = Collections.singletonList("x100");
    private List<String> multiplier5 = Collections.singletonList("x50");
    private List<String> multiplier6 = Collections.singletonList("x20");

    private SymbolsData bitcoin_cash = new SymbolsData(true, true,"Bitcoin cash", time1, multiplier1, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fgreen_bitcoin.png?alt=media", "Bitcoin Cash is a cryptocurrency that derived from Bitcoin Classic. The debate first started in 2010 and the discussion was about the limit of 1 MB per minute should be raised or not. If the limit was raised then the digital currency would grow in accordance with demand but it might lead in centralization because of the accelerated growth. Be that as it may, the organic growth of Bitcoin started to push that limit. The Bitcoin Cash supporters seized this opportunity and eventually created the Bitcoin Cash which has as a limit 8 MB per minutes adopting also a new ticker BCH.","Crypto");
    private SymbolsData ethereum = new SymbolsData(false, true,"Ethereum", time1, multiplier1, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fetherum.png?alt=media", "Ethereum is a decentralized platform for applications that run exactly as programmed without any chance of fraud, censorship or third-party interference.","Crypto");
    private SymbolsData litecoin = new SymbolsData(false, true,"Litecoin", time1, multiplier1, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2FLitecoin.png?alt=media", "Litecoin is an alternative cryptocurrency based on the model of Bitcoin. Litecoin was created by Charlie Lee, a MIT graduate. Litecoin is a p2p currency. It is decentralized and based on an open source, global payment network.","Crypto");
    private SymbolsData eos = new SymbolsData(false, true,"EOS", time1, multiplier2, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Feos.png?alt=media", "EOS is a software that introduces new blockchain architecture designed to enable vertical and horizontal scaling of decentralized applications. It provides authentication, databases, accounts, asynchronous communication and the scheduling of applications across multiple CPU cores and/or clusters.","Crypto");
    private SymbolsData ripple = new SymbolsData(false, true,"Ripple", time1, multiplier2, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fripple.png?alt=media", "Ripple is an electronic currency exchange network surrounded by a shared public ledger that allows for payment and exchanges, build mostly for enterprises. Ripple was released in 2012 and supports secure, fast and low-cost payment services in fiat currency, cryptocurrency and commodities. It is the 3rd largest cryptocurrency and holds a 10.82% market share of the whole cryptocurrency market. With its efficiency in generating new revenue and being the fastest and most extensive asset allowing for real-time payment around the world, this has attracted payment providers, especially banks that benefit from nostro accounts.","Crypto");
    private SymbolsData stellar = new SymbolsData(false, true,"Stellar", time1, multiplier2, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fstellar.png?alt=media", "Stellar is an open-source hybrid blockchain for value exchange, facilitating payments and cross-asset transfers of value. Its network allows for all individuals to have uniform access and economic engagement.","Crypto");
    private SymbolsData bitcoin = new SymbolsData(false, true,"Bitcoin", time1, multiplier3, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fbitcoin.jpg?alt=media", "Bitcoin is an original digital payment network and is regarded as a new kind of electronic currency that is called cryptocurrency. Bitcoin is the initiator of cryptocurrencies that was released in 2009 and holds about 39% of the total cryptocurrency market. The digital system has been created from a group of unknown programmers called Satoshi Nakamoto. The cryptocurrency has no authority under any central bank (decentralized) and is a peer-to-peer technology where transactions occur between users directly in a public distribution ledger known as blockchain. The Bitcoin protocol has many features, not just sending money from one point to another, but also through an easy accessible and fast way at very high secure levels when used properly.","Crypto");
    private SymbolsData bitcoin_x100 = new SymbolsData(false, true,"Bitcoin x100", time1, multiplier4, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2FBitcoinX100.png?alt=media", "Bitcoin is an original digital payment network and is regarded as a new kind of electronic currency that is called cryptocurrency. Bitcoin is the initiator of cryptocurrencies that was released in 2009 and holds about 39% of the total cryptocurrency market. The digital system has been created from a group of unknown programmers called Satoshi Nakamoto. The cryptocurrency has no authority under any central bank (decentralized) and is a peer-to-peer technology where transactions occur between users directly in a public distribution ledger known as blockchain. The Bitcoin protocol has many features, not just sending money from one point to another, but also through an easy accessible and fast way at very high secure levels when used properly.","Crypto");
    private SymbolsData binance_coin_x50 = new SymbolsData(false, true,"Binance Coin x50", time1, multiplier5, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fbinance.jpg?alt=media", "The Binance Coin (BNB) runs natively on the ERC 20 protocol in Ethereum Blockchain. The Binance Exchange behind the token is one of the largest cryptoexchanges in the world lending its credibility on BNB. One of the ways the coin can be used is as gas for transactions in the Binance Ecosystem including paying trading commission in the Binance cryptoexchange. Under the announced repurchase plan, every quarter Binance will use up to 20% of its profits to purchase back BNB and destroy them until the total supply is cut to half.","Crypto");
    private SymbolsData ethereum_x50 = new SymbolsData(false, true,"Ethereum x50", time1, multiplier5, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2FEthereum%20x50.png?alt=media", "Ethereum is a decentralized platform for applications that run exactly as programmed without any chance of fraud, censorship or third-party interference.","Crypto");
    private SymbolsData tron_x50 = new SymbolsData(false, true,"TRON x50", time1, multiplier5, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2FTRON%20x50.png?alt=media","Tron is a decentralized open-source cryptocurrency that attempts to construct a globalized free content entertainment system by utilizing its blockchain technology. Tron’s protocol permits its customers to own, publish and store data freely.","Crypto");
    private SymbolsData bitcoin_cash_x20 = new SymbolsData(false, true,"Bitcoin Cash x20", time1, multiplier6, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fgreen_bitcoin.png?alt=media", "Bitcoin Cash is a cryptocurrency that derived from Bitcoin Classic. The debate first started in 2010 and the discussion was about the limit of 1 MB per minute should be raised or not. If the limit was raised then the digital currency would grow in accordance with demand but it might lead in centralization because of the accelerated growth. Be that as it may, the organic growth of Bitcoin started to push that limit. The Bitcoin Cash supporters seized this opportunity and eventually created the Bitcoin Cash which has as a limit 8 MB per minutes adopting also a new ticker BCH.","Crypto");
    private SymbolsData cardano_x20 = new SymbolsData(false, true,"Cardano x20", time1, multiplier6, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fcardano.jpg?alt=media", "Cardano is a decentralized public blockchain and cryptocurrency project and a fully open source. Cardano is developing a smart contract platform which seeks to deliver more advanced features than any protocol previously developed. It is the first blockchain platform to evolve out of a scientific philosophy and a research-first driven approach.","Crypto");
    private SymbolsData cosmos_x20 = new SymbolsData(false, true,"Cosmos x20", time1, multiplier6, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fcosmos.jpg?alt=media", "Cosmos is a unique blockchain network with an innovative protocol that addresses successfully the three main blockchain hurdles of scalability, usability and interoperability.","Crypto");
    private SymbolsData eos_x20 = new SymbolsData(false, true,"EOS x20", time1, multiplier6, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Feos.png?alt=media", "EOS is a software that introduces new blockchain architecture designed to enable vertical and horizontal scaling of decentralized applications. It provides authentication, databases, accounts, asynchronous communication and the scheduling of applications across multiple CPU cores and/or clusters.","Crypto");
    private SymbolsData iota_x20 = new SymbolsData(false, true,"IOTA x20", time1, multiplier6, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fiota.png?alt=media", "IOTA  is a brand new and novel micro-transaction cryptotoken optimized for the Internet-of-Things (IoT). IOTA enables companies to explore new business-2-business models by making every technological resource a potential service to be traded on an open market in real time, with no fees.","Crypto");
    private SymbolsData litecoin_x20 = new SymbolsData(false, true,"Litecoin x20", time1, multiplier6, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2FLitecoin.png?alt=media", "Litecoin is an alternative cryptocurrency based on the model of Bitcoin. Litecoin was created by Charlie Lee, a MIT graduate. Litecoin is a p2p currency. It is decentralized and based on an open source, global payment network.","Crypto");
    private SymbolsData nem_x20 = new SymbolsData(false, true,"NEM x20", time1, multiplier6, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fnem.png?alt=media", "NEM is a peer-to-peer cryptocurrency and blockchain platform built on an improved blockchain technology. It integrates concepts from other cryptocurrencies like Bitcoin together with academic research in network theory. Financial institutions and private companies in Japan and internationally, are testing the commercial version of NEM which allows multiple ledgers to coexist on one blockchain. Furthermore, the technology enables the creation of smart assets through the creation of mosaics which can represent any asset, like currency, stocks and other.","Crypto");
    private SymbolsData neo_x20 = new SymbolsData(false, true,"NEO x20", time1, multiplier6, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fneo.png?alt=media", "NEO is a blockchain non-profit project and cryptocurrency that exploits the technology of blockchain allowing for the development of digital assets and smart contract.","Crypto");
    private SymbolsData ontology_x20 = new SymbolsData(false, true,"Ontology x20", time1, multiplier6, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2FOntology%20x20.png?alt=media", "Ontology is an innovative distributed ledger that incorporates multiple trust types in one integrated protocol system.","Crypto");
    private SymbolsData ripple_x20 = new SymbolsData(false, true,"Ripple x20", time1, multiplier6, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2FRipple%20x20.png?alt=media","Ripple is an electronic currency exchange network surrounded by a shared public ledger that allows for payment and exchanges, build mostly for enterprises. Ripple was released in 2012 and supports secure, fast and low-cost payment services in fiat currency, cryptocurrency and commodities. It is the 3rd largest cryptocurrency and holds a 10.82% market share of the whole cryptocurrency market. With its efficiency in generating new revenue and being the fastest and most extensive asset allowing for real-time payment around the world, this has attracted payment providers, especially banks that benefit from nostro accounts.","Crypto");
    private SymbolsData stellar_x20 = new SymbolsData(false, true,"Stellar x20", time1, multiplier6, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fstellar%20x20.png?alt=media", "Stellar is an open-source hybrid blockchain for value exchange, facilitating payments and cross-asset transfers of value. Its network allows for all individuals to have uniform access and economic engagement.","Crypto");
    private SymbolsData ethereum_classic = new SymbolsData(false, true,"Ethereum Classic", time1, multiplier2, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fethereum_classic.png?alt=media", "Ethereum Classic is a follow-up version of the original Ethereum blockchain after the fork of Ethereum platform, where two versions of Ethereum have been created; Ethereum Classic is a decentralized platform, featuring smart contracts (scripting); applications that run without any possibility of downtime, censorship, fraud or third-party interference.","Crypto");
    private SymbolsData omiseGo = new SymbolsData(false, true,"OmiseGo", time1, multiplier2, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fomisego.png?alt=media", "OmiseGo was issued by Omise that is a venture-backed payments company which operates in Japan, Thailand, Singapore and Indonesia and planning to expand further across Asia-Pacific. OmiseGo is a public Ethereum-based financial technology for use in mainstream digital wallets, that enables real-time, peer-to-peer value exchange and payment services agnostically across jurisdictions and organizational silos, and across both fiat money and decentralized currencies.","Crypto");
    private SymbolsData qtum = new SymbolsData(false, true,"Qtum", time1, multiplier2, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fqtum.png?alt=media", "Qtum smooths out characteristics of Bitcoin and Ethereum in the sense of execution of smart contracts and decentralized applications while also assisting a more practical stake consensus mechanism. Its blockchain’s purpose is to widen and strengthen its array of services in order to cover the gap between the blockchain tech and business world.","Crypto");
    private SymbolsData tron = new SymbolsData(false, true,"TRON", time1, multiplier1, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Ftron.png?alt=media", "Tron is a decentralized open-source cryptocurrency that attempts to construct a globalized free content entertainment system by utilizing its blockchain technology. Tron’s protocol permits its customers to own, publish and store data freely.","Crypto");
    private SymbolsData dash = new SymbolsData(false, true,"DASH", time1, multiplier2, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fdash.png?alt=media", "Dash (formerly known as Darkcoin and XCoin) is an open source peer-to-peer cryptocurrency that offers all the same features as Bitcoin but also has advanced capabilities, including instant transactions (InstantSend), private transactions (PrivateSend) and decentralized governance (DGBB). Dash's decentralized governance and budgeting system makes it the first decentralized autonomous organization.","Crypto");
    private SymbolsData zCash = new SymbolsData(false, true,"ZCash", time1, multiplier2, "https://firebasestorage.googleapis.com/v0/b/idnbin.appspot.com/o/uploads%2Fsymbols%2Fzcash.png?alt=media", "Zcash is a cryptocurrency aimed at using cryptography to provide enhanced privacy for its users compared to other cryptocurrencies such as Bitcoin. As a result, Zcash payments are published on a public blockchain, but users are able to use an optional privacy feature to conceal the sender, recipient, and amount being transacted. Like Bitcoin, Zcash has a fixed total supply of 21 million units.In addition, Zcash gives the transactors the option of selective disclosure that allows a user to provide proof of payment for auditing and regulatory purposes.","Crypto");

    private ArrayList<SymbolsData> cryptolist = new ArrayList<>(Arrays.asList(bitcoin_cash,ethereum,litecoin,eos,ripple,stellar,bitcoin,bitcoin_x100,binance_coin_x50,
            ethereum_x50,tron_x50,bitcoin_cash_x20,cardano_x20,cosmos_x20,eos_x20,iota_x20,litecoin_x20,nem_x20,neo_x20,ontology_x20,ripple_x20,
            stellar_x20,ethereum_classic,omiseGo,qtum,tron,dash,zCash));

    public ArrayList<SymbolsData> getCryptolist() {
        return cryptolist;
    }
}