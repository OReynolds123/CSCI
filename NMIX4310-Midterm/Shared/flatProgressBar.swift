//
//  flatProgressBar.swift
//  Midterm
//
//  Created by Owen Reynolds on 3/3/22.
//

import SwiftUI

struct flatProgressBar: View {
    var c: Double
    var l: Double
    var h: Double
    var color: Color
            
    var body: some View {
        VStack {
            HStack {
                Text("\(l, specifier: "%.2f")")
                    .font(.body)
                    .fontWeight(.regular)
                    .foregroundColor(.secondary)
                Spacer()
                Text("\(h, specifier: "%.2f")")
                    .font(.body)
                    .fontWeight(.regular)
                    .foregroundColor(.secondary)
            }
            .padding(.horizontal, 2.0)
            .offset(x:0, y:10)
                            
            ZStack(alignment: .leading) {
                Rectangle()
                    .frame(width: .infinity , height: 20)
                    .foregroundColor(Color(UIColor.secondarySystemBackground))
                    .cornerRadius(10.0)
                
                ZStack {
                    Rectangle()
                        .frame(width: 10, height: 20 / 2)
                        .foregroundColor(color)
                        .cornerRadius(10)
                    
                    Text("$\(c, specifier: "%.2f")")
                        .font(.body)
                        .fontWeight(.regular)
                        .foregroundColor(color)
                        .padding(.horizontal, 10.0)
                        .padding(.vertical, 5.0)
                        .background(Color(UIColor.tertiarySystemBackground))
                        .cornerRadius(10)
                        .overlay(
                            RoundedRectangle(cornerRadius: 10)
                                .stroke(Color(UIColor.systemGray4), lineWidth: 1)
                        )
                        .frame(width: 100)
                        .offset(x:0, y:22)
                }
                .offset(x: (((c-l) / (h-l)) * 255) - 38, y: 0)
                .animation(.linear(duration: 0.5), value: c)
            }
            Spacer()
        }
        .frame(width: .infinity, height:65)
        .offset(x:0, y:-10)
    }
}

struct flatProgressBar_Previews: PreviewProvider {
    static var previews: some View {
        flatProgressBar(c: 0.0, l: 0.0, h: 100.0, color: Color.gray)
            .frame(width: 280, height: 65)
    }
}
