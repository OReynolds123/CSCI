//
//  ContentView.swift
//  Shared
//
//  Created by Owen Reynolds on 2/22/22.
//

import SwiftUI

struct ContentView: View {
    @ObservedObject var data = getSymbolData()
    
    var body: some View {
        NavigationView {
            VStack {
                ZStack {
                    ScrollView(.horizontal, showsIndicators: true) {
                        HStack(alignment: .top) {
                            Group {
                                NavigationLink(destination:
                                    VStack {
                                        cardViewClickFunc(data: data.stock, val: 3, name: "DOW")
                                        Spacer()
                                    }
                                ) {
                                    cardView(logo: data.stock.response.dji.logo, name: "DOW", current: data.stock.response.dji.current, change: data.stock.response.dji.change, changepct: data.stock.response.dji.changepct)
                                }
                                NavigationLink(destination:
                                    VStack {
                                        cardViewClickFunc(data: data.stock, val: 6, name: "S&P")
                                        Spacer()
                                    }
                                ) {
                                    cardView(logo: data.stock.response.inx.logo, name: "S&P", current: data.stock.response.inx.current, change: data.stock.response.inx.change, changepct: data.stock.response.inx.changepct)
                                }
                                NavigationLink(destination:
                                    VStack {
                                        cardViewClickFunc(data: data.stock, val: 7, name: "IXIC")
                                        Spacer()
                                    }
                                ) {
                                    cardView(logo: data.stock.response.ixic.logo, name: "IXIC", current: data.stock.response.ixic.current, change: data.stock.response.ixic.change, changepct: data.stock.response.ixic.changepct)
                                }
                                NavigationLink(destination:
                                    VStack {
                                        cardViewClickFunc(data: data.stock, val: 9, name: "RUT")
                                        Spacer()
                                    }
                                ) {
                                    cardView(logo: data.stock.response.rut.logo, name: "RUT", current: data.stock.response.rut.current, change: data.stock.response.rut.change, changepct: data.stock.response.rut.changepct)
                                }
                            }
                            .frame(width: 320)
                            .padding(.horizontal)
                        }
                        .padding(.horizontal)
                    }
                }
                .offset(x:0, y:-130)
                
                ZStack {
                    ScrollView(.horizontal, showsIndicators: false) {
                        HStack(alignment: .center) {
                            Group {
                                cardViewClickFunc(data: data.stock, val: 0, name: "AAPL")
                                cardViewClickFunc(data: data.stock, val: 1, name: "AMZN")
                                cardViewClickFunc(data: data.stock, val: 2, name: "DIS")
                                cardViewClickFunc(data: data.stock, val: 4, name: "FB")
                                cardViewClickFunc(data: data.stock, val: 5, name: "GOOGL")
                                cardViewClickFunc(data: data.stock, val: 8, name: "MSFT")
                                cardViewClickFunc(data: data.stock, val: 10, name: "TSLA")
                            }
                            .frame(width: 340)
                            .padding(.horizontal)
                        }
                        .padding(.horizontal)
                    }
                }
                .offset(x:0, y:-120)
                
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

struct cardViewClickFunc: View {
    var data: stockLayout
    var val: Int
    var name: String
    
    var body: some View {
        let dataArr = [data.response.aapl, data.response.amzn, data.response.dis, data.response.dji, data.response.fb, data.response.googl, data.response.inx, data.response.ixic, data.response.msft, data.response.rut, data.response.tsla]
        
        cardViewClick(name: name, current: dataArr[val].current, change: dataArr[val].change, changepct: dataArr[val].changepct, high: dataArr[val].high, low: dataArr[val].low, op: dataArr[val].op, close: dataArr[val].close, high52: dataArr[val].high52, low52: dataArr[val].low52, volume: dataArr[val].volume, marketcap: dataArr[val].marketcap, logo: dataArr[val].logo, desc: dataArr[val].desc)
    }
}
