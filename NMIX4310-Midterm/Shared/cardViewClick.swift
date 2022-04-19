//
//  cardViewClick.swift
//  Midterm
//
//  Created by Owen Reynolds on 3/3/22.
//

import SwiftUI

struct cardViewClick: View {
    @State var pressed = false
    
    var name: String
    var current: Double
    var change: Double
    var changepct: Double
    var high: Double
    var low: Double
    var op: Double
    var close: Double
    var high52: Double
    var low52: Double
    var volume: Int
    var marketcap: Int
    var logo: String
    var desc: String
    
    var body: some View {
        let color1 = getColor(change: change)
        let rotate1 = getRotate(change: change)
        
        VStack(alignment: .center) {
            HStack(alignment: .center) {
                VStack(alignment: .leading) {
                    Text(name)
                        .font(.title)
                        .fontWeight(.black)
                        .foregroundColor(.primary)
                        .minimumScaleFactor(0.5)
                    Text("$\(current, specifier: "%.2f")")
                        .font(.largeTitle)
                        .fontWeight(.medium)
                        .foregroundColor(color1)
                        .minimumScaleFactor(0.5)
                    HStack {
                        ZStack{
                            Text("⌃")
                                .font(.title2)
                                .fontWeight(.medium)
                                .foregroundColor(color1)
                                .minimumScaleFactor(0.5)
                                .offset(x: 0, y: 3)
                                .rotationEffect(Angle(degrees: rotate1))
                                .animation(.linear(duration: 0.5), value: rotate1)
                        }
                        Text("\(changepct, specifier: "%.2f")%")
                            .font(.title3)
                            .fontWeight(.medium)
                            .foregroundColor(color1)
                            .minimumScaleFactor(0.5)
                        Text("(\(change, specifier: "%.2f"))")
                            .font(.headline)
                            .fontWeight(.medium)
                            .foregroundColor(color1)
                            .minimumScaleFactor(0.5)
                    }
                }
                Spacer()
                AsyncImage(url: URL(string: logo)) { image in
                    image.resizable()
                } placeholder: {
                    ProgressView()
                }
                .cornerRadius(10)
                .frame(width: 90, height: 90)
            }
            .padding(.bottom)
            
            if (pressed == false) {
                VStack {
                    VStack(alignment: .center) {
                        Text("Daily Low/High")
                            .font(.headline)
                            .fontWeight(.regular)
                            .foregroundColor(.primary)
                            .offset(x:0, y:15)
                        flatProgressBar(c: current, l: low, h: high, color: color1)
                            .frame(width: .infinity, height: 65)
                        Spacer()
                    }
                    .frame(width: 280, height: 90)
                }
                .padding(.bottom)
                
                HStack(alignment: .center) {
                    VStack(alignment: .leading) {
                        Text("Open:")
                            .font(.headline)
                            .fontWeight(.regular)
                            .foregroundColor(.primary)
                            .minimumScaleFactor(0.5)
                        Text("Close:")
                            .font(.headline)
                            .fontWeight(.regular)
                            .foregroundColor(.primary)
                            .minimumScaleFactor(0.5)
                        Text("Yearly High:")
                            .font(.headline)
                            .fontWeight(.regular)
                            .foregroundColor(.primary)
                            .minimumScaleFactor(0.5)
                        Text("Yearly Low:")
                            .font(.headline)
                            .fontWeight(.regular)
                            .foregroundColor(.primary)
                            .minimumScaleFactor(0.5)
                        Text("Volume:")
                            .font(.headline)
                            .fontWeight(.regular)
                            .foregroundColor(.primary)
                            .minimumScaleFactor(0.5)
                        Text("Market Cap:")
                            .font(.headline)
                            .fontWeight(.regular)
                            .foregroundColor(.primary)
                            .minimumScaleFactor(0.5)
                    }
                    .padding(.trailing)
                    VStack(alignment: .leading) {
                        Text("$\(op, specifier: "%.2f")")
                            .font(.headline)
                            .fontWeight(.regular)
                            .foregroundColor(.secondary)
                            .minimumScaleFactor(0.5)
                        Text("$\(close, specifier: "%.2f")")
                            .font(.headline)
                            .fontWeight(.regular)
                            .foregroundColor(.secondary)
                            .minimumScaleFactor(0.5)
                        Text("$\(high52, specifier: "%.2f")")
                            .font(.headline)
                            .fontWeight(.regular)
                            .foregroundColor(.secondary)
                            .minimumScaleFactor(0.5)
                        Text("$\(low52, specifier: "%.2f")")
                            .font(.headline)
                            .fontWeight(.regular)
                            .foregroundColor(.secondary)
                            .minimumScaleFactor(0.5)
                        Text("\(volume)")
                            .font(.headline)
                            .fontWeight(.regular)
                            .foregroundColor(.secondary)
                            .minimumScaleFactor(0.5)
                        Text("$\(marketcap)")
                            .font(.headline)
                            .fontWeight(.regular)
                            .foregroundColor(.secondary)
                            .minimumScaleFactor(0.5)
                    }
                }
                .padding(.bottom)
            } else {
                Text(desc)
                    .font(.headline)
                    .fontWeight(.regular)
                    .foregroundColor(.primary)
                    .multilineTextAlignment(.center)
                    .minimumScaleFactor(0.5)
                    .lineLimit(10)
            }
            
            Button {
                pressed.toggle()
            } label: {
                if (pressed) {
                    companyBtn(txt: "Stock")
                } else {
                    companyBtn(txt: "Company Info")
                }
            }
        }
        .padding()
        .cornerRadius(10)
        .overlay(
            RoundedRectangle(cornerRadius: 10)
                .stroke(Color("borderColor"), lineWidth: 1)
        )
        .padding(.all)
        .frame(width: 400)
    }
}

struct cardViewClick_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            cardViewClick(name: "AAPL", current: 180.0, change: 10.0, changepct: 2.00, high: 190.00, low: 174.00, op: 170.00, close: 165.00, high52: 200.00, low52: 150.00, volume: 100000000, marketcap: 1000000000, logo: "https://static.finnhub.io/logo/87cb30d8-80df-11ea-8951-00000000092a.png", desc: "Apple Inc. is an American multinational technology company that specializes in consumer electronics, software and online services.")
            Spacer()
        }
    }
}

struct companyBtn: View {
    var txt: String
    
    var body: some View {
        Text(txt)
            .font(.body)
            .fontWeight(.regular)
            .foregroundColor(.primary.opacity(0.85))
            .padding()
            .background(Color(UIColor.secondarySystemBackground).opacity(0.5))
            .cornerRadius(10)
            .overlay(
                RoundedRectangle(cornerRadius: 10)
                    .stroke(Color(UIColor.systemGray4), lineWidth: 1)
            )
    }
}
