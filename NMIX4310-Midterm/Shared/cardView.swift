//
//  cardView.swift
//  Midterm
//
//  Created by Owen Reynolds on 3/1/22.
//

import SwiftUI

struct cardView: View {
    var logo: String
    var name: String
    var current: Double
    var change: Double
    var changepct: Double
    
    var body: some View {
        let color1 = getColor(change: change)
        let rotate1 = getRotate(change: change)
        
        HStack {
            VStack(alignment: .leading) {
                HStack {
                    Text(name)
                        .font(.title)
                        .fontWeight(.black)
                        .foregroundColor(.primary)
                    Spacer()
                    Text("\(current, specifier: "%.2f")")
                        .font(.title2)
                        .fontWeight(.medium)
                        .foregroundColor(color1)
                }
                .padding(.bottom, 0.0)
                HStack {
                    ZStack{
                        Text("⌃")
                            .font(.title2)
                            .fontWeight(.medium)
                            .foregroundColor(color1)
                            .offset(x: 0, y: 3)
                            .rotationEffect(Angle(degrees: rotate1))
                            .animation(.linear(duration: 0.5), value: rotate1)
                    }
                    Text("\(changepct, specifier: "%.2f")%")
                        .font(.title3)
                        .fontWeight(.medium)
                        .foregroundColor(color1)
                    Spacer()
                    Text("(\(change, specifier: "%.2f"))")
                        .font(.headline)
                        .fontWeight(.medium)
                        .foregroundColor(color1)
                }
            }
            .padding([.top, .leading, .bottom])
            Spacer()
            AsyncImage(url: URL(string: logo)) { image in
                image.resizable()
            } placeholder: {
                ProgressView()
            }
            .padding(.all, 5.0)
            .background(Color(UIColor.secondarySystemBackground).opacity(0.5))
            .cornerRadius(10)
            .frame(width: 80, height: 80)
        }
        .padding()
        .cornerRadius(10)
        .overlay(
            RoundedRectangle(cornerRadius: 10)
                .stroke(Color("borderColor"), lineWidth: 1)
        )
        .padding(.all)
        .frame(width: 375)
    }
}

struct cardView_Previews: PreviewProvider {
    static var previews: some View {
        cardView(logo: "https://static.finnhub.io/logo/87cb30d8-80df-11ea-8951-00000000092a.png", name: "AAPL", current: 0.00, change: 0.00, changepct: 0.00)
    }
}

func getColor(change: Double) -> Color {
    if (change < 0) {
        return Color.red
    } else if (change > 0) {
        return Color.green
    } else {
        return Color.gray
    }
}
func getRotate(change: Double) -> Double {
    if (change < 0) {
        return 180.0
    } else if (change > 0) {
        return 0.0
    } else {
        return 90.0
    }
}
